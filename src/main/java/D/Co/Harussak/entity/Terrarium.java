package D.Co.Harussak.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Terrarium")
public class Terrarium {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @Lob
    private String layout; // JSON 저장
}
