package in.iot.lab.innovance.service;

import in.iot.lab.innovance.dto.Message;
import in.iot.lab.innovance.dto.OpenAIRequest;
import in.iot.lab.innovance.dto.OpenAIResponse;
import in.iot.lab.innovance.entity.UserLevelChoice;
import in.iot.lab.innovance.repository.UserLevelChoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OpenAIService {
    
    private final UserLevelChoiceRepository userLevelRepo;
    private final WebClient.Builder webClientBuilder;
    
    @Value("${openai.api.key}")
    private String openAIApiKey;
    
    private WebClient getWebClient() {
        return webClientBuilder
                .baseUrl("https://api.openai.com/v1/chat/completions")
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + openAIApiKey)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
    
    public Mono<String> getOpenAIResponse(Integer userId) {
        List<UserLevelChoice> choices = userLevelRepo.findByUser_Id(userId);
        if (choices.isEmpty()) {
            return Mono.error(new IllegalArgumentException("No choices found for user ID: " + userId));
        }
        String prompt = PromptBuilder.buildPrompt(choices);
        OpenAIRequest requestBody = new OpenAIRequest(
                "gpt-4o-mini",
                List.of(new Message("user", prompt)),
                50);
        
        return getWebClient().post()
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(OpenAIResponse.class)
                .flatMap(response -> {
                    if (response != null && response.getChoices() != null && !response.getChoices().isEmpty()) {
                        return Mono.just(response.getChoices().getFirst().getMessage().getContent().trim());
                    } else {
                        return Mono.error(new RuntimeException("Received an empty response from OpenAI API"));
                    }
                });
    }
}
