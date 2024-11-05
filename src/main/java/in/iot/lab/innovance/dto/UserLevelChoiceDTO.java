package in.iot.lab.innovance.dto;


import in.iot.lab.innovance.entity.Level;
import in.iot.lab.innovance.entity.UserLevelChoice;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserLevelChoiceDTO {
    private Integer id;
    private UserDTO user;
    private Level level;
    private Integer selected;

    public UserLevelChoice toUserLevelChoice() {
        return UserLevelChoice
                .builder()
                .id(id)
                .user(user.toUser())
                .level(level)
                .selected(selected)
                .build();
    }
}