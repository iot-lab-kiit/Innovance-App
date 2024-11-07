package in.iot.lab.innovance.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Choice {
    private int index;
    private Message message;
    private String finish_reason;
}