package e.authservice.dto.response;

import e.authservice.entity.User;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponseData {
    private UserDto user;
    private String token;
}