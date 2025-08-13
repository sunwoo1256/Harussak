package D.Co.Harussak.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "User")
public class User {
    @Id
    private Long id;

    private String email;
    private String password;
    private String nickname;
    private java.time.LocalDateTime joinedAt;
    private Long seeds;

    @Enumerated(EnumType.STRING)
    private Role roles;

    public enum Role {
        ROLE_ADMIN, ROLE_USER
    }
}

