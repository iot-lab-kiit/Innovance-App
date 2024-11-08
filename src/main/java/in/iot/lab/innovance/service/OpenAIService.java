package in.iot.lab.innovance.service;

import in.iot.lab.innovance.dto.Message;
import in.iot.lab.innovance.dto.OpenAIRequest;
import in.iot.lab.innovance.dto.OpenAIResponse;
import in.iot.lab.innovance.entity.UserLevelChoice;
import in.iot.lab.innovance.repository.UserLevelChoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OpenAIService {
    
    private final UserLevelChoiceRepository userLevelRepo;
    private final WebClient.Builder webClientBuilder;
    private final RateLimiterService rateLimiterService;
    private final StringRedisTemplate stringRedisTemplate;
    
    @Value("${openai.api.key}")
    private String openAIApiKey;
    
    @Value("${cache.invalidate.seconds}")
    private int cacheInvalidateSeconds;
    
    private WebClient getWebClient() {
        return webClientBuilder
                .baseUrl("https://api.openai.com/v1/chat/completions")
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + openAIApiKey)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
    
    // Method to generate a unique cache key based on userId and choices
    private String generateCacheKey(Integer userId, String prompt) {
        return "openai:response:" + userId + ":" + prompt.hashCode();
    }
    
    public Mono<String> getOpenAIResponse(Integer userId) {
        return rateLimiterService.isAllowed(userId)
                .flatMap(isAllowed -> {
                    if (!isAllowed) {
                        return Mono.error(new RuntimeException("Rate limit exceeded. Try again later."));
                    }
                    
                    
                    List<UserLevelChoice> choices = userLevelRepo.findByUser_Id(userId);
                    if (choices.isEmpty()) {
                        return Mono.error(new IllegalArgumentException("No choices found for user ID: " + userId));
                    }
                    String prompt = PromptBuilder.buildPrompt(choices);
                    
                    // Check cache first before making the request
                    String cacheKey = generateCacheKey(userId, prompt);
                    String cachedResponse = stringRedisTemplate.opsForValue().get(cacheKey);
                    if (cachedResponse != null) {
                        return Mono.just(cachedResponse);
                    }
                    
                    OpenAIRequest requestBody = new OpenAIRequest(
                            "gpt-4o-mini",
                            List.of(new Message("user", prompt)),
                            100);
                    
                    return getWebClient()
                            .post()
                            .bodyValue(requestBody)
                            .retrieve()
                            .bodyToMono(OpenAIResponse.class)
                            .flatMap(response -> {
                                if (response != null && response.getChoices() != null && !response.getChoices().isEmpty()) {
                                    String responseContent = response.getChoices().getFirst().getMessage().getContent();
                                    stringRedisTemplate.opsForValue().set(cacheKey, responseContent, Duration.ofSeconds(cacheInvalidateSeconds));
                                    return Mono.just(responseContent);
                                } else {
                                    return Mono.error(new RuntimeException("Received an empty response from OpenAI API"));
                                }
                            });
                });
    }
}
