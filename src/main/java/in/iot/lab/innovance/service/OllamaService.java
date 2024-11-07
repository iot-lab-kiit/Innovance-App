//package in.iot.lab.innovance.service;
//
//import in.iot.lab.innovance.dto.OllamaRequest;
//import in.iot.lab.innovance.dto.OllamaResponse;
//import in.iot.lab.innovance.entity.UserLevelChoice;
//import in.iot.lab.innovance.repository.UserLevelChoiceRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatusCode;
//import org.springframework.stereotype.Service;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Mono;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class OllamaService {
//
//    private final UserLevelChoiceRepository userLevelRepo;
//    private final WebClient.Builder webClientBuilder;
//
//    private WebClient getWebClient() {
//        return webClientBuilder.baseUrl("http://localhost:11434").build();
//    }
//
//    public Mono<String> getOllamaResponse(Integer userId) {
//        List<UserLevelChoice> choices = userLevelRepo.findByUser_Id(userId);
//        if (choices.isEmpty()) {
//            return Mono.error(new IllegalArgumentException("No choices found for user ID: " + userId));
//        }
//
//        String prompt = PromptBuilder.buildPrompt(choices);
//        OllamaRequest requestBody = new OllamaRequest("llama3.2", prompt, false);
//
//        return getWebClient().post()
//                .uri("/api/generate")
//                .bodyValue(requestBody)
//                .retrieve()
//                .onStatus(HttpStatusCode::isError, clientResponse ->
//                        Mono.error(new RuntimeException("Failed to call Ollama API with status: " + clientResponse.statusCode())))
//                .bodyToMono(OllamaResponse.class)
//                .flatMap(response -> {
//                    if (response != null && response.getResponseText() != null) {
//                        return Mono.just(response.getResponseText());
//                    } else {
//                        return Mono.error(new RuntimeException("Received a null response from Ollama API"));
//                    }
//                });
//
//    }
//}
