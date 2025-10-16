package D.Co.Harussak.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Achievement")
public class Achievement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    private String name;

    @Lob
    private String description;

    private Boolean status;
}
