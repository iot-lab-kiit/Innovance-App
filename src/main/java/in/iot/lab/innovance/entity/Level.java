package in.iot.lab.innovance.entity;

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
}