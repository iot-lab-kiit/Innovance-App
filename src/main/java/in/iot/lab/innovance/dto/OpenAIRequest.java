package in.iot.lab.innovance.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OpenAIRequest {
    private String model;
    private List<Message> messages;
    private int max_tokens;
    
    public OpenAIRequest(String model, List<Message> messages, int maxTokens) {
        this.model = model;
        this.messages = messages;
        this.max_tokens = maxTokens;
    }
 
}


