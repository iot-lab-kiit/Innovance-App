package in.iot.lab.innovance.dto;


import in.iot.lab.innovance.entity.Level;
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
}