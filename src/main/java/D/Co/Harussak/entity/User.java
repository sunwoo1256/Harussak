package D.Co.Harussak.entity;

import jakarta.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "User")
@Getter @Setter
public class User {
    @Id
    private Long id;

    private String email;
    private String password;
    private String nickname;
    private java.time.LocalDateTime joinedAt;
    private Long seeds;

    @Enumerated(EnumType.STRING)
    private List<Role> roles;

    public enum Role {
        ROLE_ADMIN, ROLE_USER
    }
}

