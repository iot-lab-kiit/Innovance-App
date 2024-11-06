package in.iot.lab.innovance.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import in.iot.lab.innovance.dto.UserDTO;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER_DB")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "roll_no", unique = true)
    private String rollNo;

    @ManyToOne(
            cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST},
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "domain_id")
    private Domain domain;

    public UserDTO toUserDTO() {
        return UserDTO
                .builder()
                .id(id)
                .name(name)
                .rollNo(rollNo)
                .domainId(domain != null ? domain.getId() : null)
                .build();
    }
}