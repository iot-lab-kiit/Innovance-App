package in.iot.lab.innovance.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_option")
public class Option {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String optionText;  //options
    
    @ManyToOne
    @JoinColumn(name = "level_id", nullable = false)
    private Level level;
    
}

