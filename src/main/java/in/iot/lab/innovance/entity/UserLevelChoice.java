package in.iot.lab.innovance.entity;


import in.iot.lab.innovance.dto.UserLevelChoiceDTO;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER_LEVEL_CHOICE_DB")
public class UserLevelChoice {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}
    )
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}
    )
    @JoinColumn(name = "level_id")
    private Level level;

    @Column(name = "selected")
    private Integer selected;

    public UserLevelChoiceDTO toUserLevelChoiceDto() {
        String domainName = user.getDomain() != null ? user.getDomain().getName() : null;
        return UserLevelChoiceDTO
                .builder()
                .id(id)
                .user(user.toUserDTO())
                .level(level)
                .selected(selected)
                .domainName(domainName)
                .build();
    }
}