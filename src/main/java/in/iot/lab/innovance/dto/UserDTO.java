package in.iot.lab.innovance.dto;

import in.iot.lab.innovance.entity.User;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Integer id;
    private String name;
    private String rollNo;

    public User toUser() {
        return User
                .builder()
                .id(id)
                .name(name)
                .rollNo(rollNo)
                .build();
    }
}