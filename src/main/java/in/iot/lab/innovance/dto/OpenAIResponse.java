package in.iot.lab.innovance.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OpenAIResponse {
    private String id;
    private String object;
    private int created;
    private String model;
    private List<Choice> choices;
}


