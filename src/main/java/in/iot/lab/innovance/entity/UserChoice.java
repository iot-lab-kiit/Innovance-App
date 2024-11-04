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
@Table(name = "user_choice")
public class UserChoice {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "level_id", nullable = false)
    private Level level;
    
    private String selected;
    
    
    
    public List<String> getSelected() {
        return selected != null ? Arrays.asList(selected.split(",")) : null;
    }
    
    public void setSelected(List<String> selected) {
        this.selected = String.join(",", selected);
    }
}
