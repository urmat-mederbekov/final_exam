package kg.attractor.final_exam.dto;

import kg.attractor.final_exam.model.User;
import lombok.*;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDTO {

    private String email;
    private String name;

    public static UserDTO from(User user) {
        return builder()
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }
}
