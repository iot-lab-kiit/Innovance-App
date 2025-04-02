package in.iot.lab.innovance.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GeminiRequest {
    private List<Content> contents;
    private GenerationConfig generationConfig;
    
    public GeminiRequest(String prompt, int maxTokens) {
        this.contents = List.of(new Content("user", prompt));
        this.generationConfig = new GenerationConfig(maxTokens, 0.7);
    }
    
    @Getter
    @Setter
    public static class Content {
        private List<Part> parts;
        private String role;
        
        public Content(String role, String text) {
            this.role = role;
            this.parts = List.of(new Part(text));
        }
    }
    
    @Getter
    @Setter
    public static class Part {
        private String text;
        
        public Part(String text) {
            this.text = text;
        }
    }
    
    @Getter
    @Setter
    public static class GenerationConfig {
        private int maxOutputTokens;
        private double temperature;
        
        public GenerationConfig(int maxOutputTokens, double temperature) {
            this.maxOutputTokens = maxOutputTokens;
            this.temperature = temperature;
        }
    }
}
