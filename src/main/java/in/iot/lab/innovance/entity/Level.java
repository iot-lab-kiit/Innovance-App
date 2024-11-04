package in.iot.lab.innovance.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "level")
public class Level {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String question;
    private int levelNo;
    
    @ManyToOne
    @JoinColumn(name = "domain_id", nullable = false)
    private Domain domain;
    
    private String options;
    
    
    
    public List<String> getOptions() {
        return options != null ? Arrays.asList(options.split(",")) : null;
    }
    
    public void setOptions(List<String> options) {
        this.options = String.join(",", options);
    }
}
