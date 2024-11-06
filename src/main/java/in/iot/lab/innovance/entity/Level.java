package in.iot.lab.innovance.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "LEVEL_DB")
public class Level {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "level_no")
    private Integer levelNo;

    @Column(name = "question")
    private String question;

    @Column(name = "options")
    private List<String> options;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "domain_id", insertable = false, updatable = false)
    @JsonIgnore
    private Domain domain;

}