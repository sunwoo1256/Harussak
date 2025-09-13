package D.Co.Harussak.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "User")
@Getter @Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private String nickname;
    @CreationTimestamp
    private LocalDateTime joinedAt;
    private Long seeds = 5L;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Role> roles = new ArrayList<>();

    public User(String email, String encodedPassword, List<Role> roleUser) {
        this.email = email;
        this.password = encodedPassword;
        this.roles = roleUser;
    }

    public enum Role {
        ADMIN, USER
    }
}

