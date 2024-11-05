package in.iot.lab.innovance.dto;


import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PostUserChoiceRequest {
    private Integer userId;
    private Integer levelId;
    private Integer selected;
}