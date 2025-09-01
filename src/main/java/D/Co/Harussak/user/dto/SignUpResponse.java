package D.Co.Harussak.user.dto;

import D.Co.Harussak.entity.User;
import D.Co.Harussak.entity.User.Role;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignUpResponse {
    String email;
    List<Role> roles;

}
