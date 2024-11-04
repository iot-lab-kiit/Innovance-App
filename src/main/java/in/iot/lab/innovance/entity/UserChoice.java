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
    
    @ManyToOne
    @JoinColumn(name = "option_id", nullable = false)
    private Option chosenOption;
    
    @ManyToOne
    @JoinColumn(name = "domain_id", nullable = false)
    private Domain domain;
}

