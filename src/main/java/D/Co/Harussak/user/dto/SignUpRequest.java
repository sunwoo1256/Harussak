package D.Co.Harussak.user.dto;

import D.Co.Harussak.entity.User;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data

public class SignUpRequest {

    String email;
    String password;

    public User toUser(String encodedPassword) {
        return User.builder()
            .email(email)
            .password(encodedPassword)
            .roles(List.of(User.Role.ROLE_USER))
            .build();
    }
}


