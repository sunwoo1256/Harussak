package D.Co.Harussak.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Cultivation")
public class Cultivation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plantId", nullable = false)
    private Plant plant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "routineId", nullable = false)
    private Routine routine;

    private String emoji;
    private java.time.LocalDateTime startDate;
    private java.time.LocalDateTime endDate;

    @Lob
    private String diary;

    private Long level;
}

