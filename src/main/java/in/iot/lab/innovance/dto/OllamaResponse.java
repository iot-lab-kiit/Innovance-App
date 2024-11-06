package in.iot.lab.innovance.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class OllamaResponse {
    @JsonProperty("response")
    private String responseText;
    
    @JsonProperty("done")
    private boolean done;
    
    @JsonProperty("done_reason")
    private String doneReason;
    
    @JsonProperty("context")
    private List<Integer> context; // Assuming context is a list of integers
    
    @JsonProperty("total_duration")
    private long totalDuration;
    
    @JsonProperty("load_duration")
    private long loadDuration;
    
    @JsonProperty("prompt_eval_count")
    private int promptEvalCount;
    
    @JsonProperty("prompt_eval_duration")
    private long promptEvalDuration;
    
    @JsonProperty("eval_count")
    private int evalCount;
    
    @JsonProperty("eval_duration")
    private long evalDuration;
    
    // You can add more fields if necessary
}

