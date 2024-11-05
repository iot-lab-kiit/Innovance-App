package in.iot.lab.innovance.service;

import in.iot.lab.innovance.dto.OllamaResponse;
import in.iot.lab.innovance.dto.UserLevelChoiceDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OllamaService {
    
    private final WebClient webClient = WebClient.builder().baseUrl("http://localhost:11434").build();
    
    private final Map<String, String> domainPrompts = new HashMap<>() {{
        put("App Dev", "In the domain of App Development, you selected '%s' for level %s. Roast me on the basis of my options as brutal as you can under 100 words.");
        put("Web Dev", "In the domain of Web Development, you chose '%s' for level %s. What are the implications of this choice in your projects?");
    }};
    
    private String generatePrompt(List<UserLevelChoiceDTO> choices) {
        StringBuilder promptBuilder = new StringBuilder();
        
        for (UserLevelChoiceDTO choice : choices) {
            String optionsString = String.join(", ", choice.getLevel().getOptions());
            int selectedIndex = choice.getSelected();
            String selectedOption = (selectedIndex >= 0 && selectedIndex < choice.getLevel().getOptions().size())
                    ? choice.getLevel().getOptions().get(selectedIndex)
                    : "Invalid selection";
            
            String levelName = choice.getLevel().getQuestion();
        
            String domainPrompt = domainPrompts.getOrDefault(choice.getDomainName(),
                    "Based on your selection, provide insights regarding your choice.");
            
            promptBuilder.append(String.format(domainPrompt, selectedOption, levelName));
            promptBuilder.append(String.format(" Options available were [%s]. ", optionsString));
        }
        System.out.printf("Given the user selections: %s, roast them as brutally as you can!%n", promptBuilder.toString());
        return String.format("Given the user selections: %s, roast them as brutally as you can!", promptBuilder.toString());
    }
    
    public Mono<String> getOllamaResponse(List<UserLevelChoiceDTO> choices) {
        String prompt = generatePrompt(choices);
        
        OllamaRequest requestBody = new OllamaRequest("llama3.2", prompt, false);
        
        return webClient.post()
                .uri("/api/generate")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(OllamaResponse.class)
                .flatMap(response -> {
                    if (response != null && response.getResponseText() != null) {
                        return Mono.just(response.getResponseText());
                    } else {
                        return Mono.error(new RuntimeException("Received a null response from Ollama API"));
                    }
                });
    }
    
    private record OllamaRequest(String model, String prompt, boolean stream) {
    }
    
}
