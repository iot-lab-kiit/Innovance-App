package in.iot.lab.innovance.controller;

import in.iot.lab.innovance.constants.UrlConstants;
import in.iot.lab.innovance.service.OpenAIService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class OpenAIController {
    private final OpenAIService openAIService;
    
    @GetMapping(UrlConstants.GET_RESPONSE_BY_AI)
    public Mono<ResponseEntity<String>> getAIResponseForChoice(@PathVariable Integer id) {
        return openAIService.getOpenAIResponse(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.internalServerError().build());
    }
}
