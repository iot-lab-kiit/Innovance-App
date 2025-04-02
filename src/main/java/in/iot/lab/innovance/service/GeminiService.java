package in.iot.lab.innovance.service;

import in.iot.lab.innovance.dto.GeminiRequest;
import in.iot.lab.innovance.dto.GeminiResponse;
import in.iot.lab.innovance.entity.UserLevelChoice;
import in.iot.lab.innovance.repository.UserLevelChoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GeminiService {
    
    private final UserLevelChoiceRepository userLevelRepo;
    private final WebClient.Builder webClientBuilder;
    
    @Value("${gemini.api.key}")
    private String geminiApiKey;
    
    @Value("${gemini.model}")
    private String geminiModel;
    
    private WebClient getWebClient() {
        // The correct URL format for Gemini API
        return webClientBuilder
                .baseUrl("https://generativelanguage.googleapis.com/v1/models/" + geminiModel + ":generateContent")
                .build();
    }
    
    public Mono<Map<String, Object>> getGeminiResponse(Integer userId) {
        
        List<UserLevelChoice> choices = userLevelRepo.findByUser_Id(userId);
        if (choices.isEmpty()) {
            return Mono.error(new IllegalArgumentException("No choices found for user ID: " + userId));
        }
        
        String prompt = PromptBuilder.buildPrompt(choices);
        
        GeminiRequest requestBody = new GeminiRequest(prompt, 500);
        
        return getWebClient()
                .post()
                .uri(uriBuilder -> uriBuilder.queryParam("key", geminiApiKey).build())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(GeminiResponse.class)
                .flatMap(response -> {
                    if (response != null && response.getCandidates() != null && !response.getCandidates().isEmpty()) {
                        String responseContent = response.getCandidates().getFirst().getContent().getParts().getFirst().getText();
                        
                        // Prepare JSON response
                        Map<String, Object> jsonResponse = new HashMap<>();
                        jsonResponse.put("status", "success");
                        jsonResponse.put("data", responseContent);
                        
                        return Mono.just(jsonResponse);
                    } else {
                        return Mono.error(new RuntimeException("Received an empty response from Gemini API"));
                    }
                });
    }
}