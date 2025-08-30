package D.Co.Harussak.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "User")
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private Long id;

    private String email;
    private String password;
    private String nickname;
    private LocalDateTime joinedAt;
    private Long seeds;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Role> roles = new ArrayList<>();

    public enum Role {
        ROLE_ADMIN, ROLE_USER
    }
}

