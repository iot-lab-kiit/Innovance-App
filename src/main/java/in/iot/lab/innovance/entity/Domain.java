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
@Table(name = "DOMAIN_DB")
public class Domain {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", unique = true)
    private String name;

    @OneToMany(
            mappedBy = "domain",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Level> levels;

    public void addLevel(Level level) {
        if (!levels.contains(level)) {
            levels.add(level);
            level.setDomain(this);
        }
    }

    public void removeLevel(Level level) {
        if (levels.contains(level)) {
            levels.remove(level);
            level.setDomain(null);
        }
    }
}