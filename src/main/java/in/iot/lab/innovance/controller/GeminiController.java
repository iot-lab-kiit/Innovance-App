package in.iot.lab.innovance.controller;

import in.iot.lab.innovance.constants.UrlConstants;
import in.iot.lab.innovance.service.GeminiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class GeminiController {
    private final GeminiService geminiService;
    
    @GetMapping(UrlConstants.GET_RESPONSE_BY_GEMINI_AI)
    public Mono<ResponseEntity<Map<String, Object>>> getAIResponseForChoice(@PathVariable Integer id) {
        return geminiService.getGeminiResponse(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.internalServerError().build());
    }
}