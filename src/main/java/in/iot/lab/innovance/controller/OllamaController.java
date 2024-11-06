package in.iot.lab.innovance.controller;

import in.iot.lab.innovance.constants.UrlConstants;
import in.iot.lab.innovance.service.OllamaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OllamaController {
    private final OllamaService ollamaService;
    
    @GetMapping(UrlConstants.GET_RESPONSE_BY_AI)
    public ResponseEntity<String> getAIResponseForChoice(@PathVariable Integer id) {
        return ollamaService.getOllamaResponse(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.internalServerError().build())
                .block();
    }
}
