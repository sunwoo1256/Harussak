package D.Co.Harussak.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "RoutineExecution")
public class RoutineExecution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "routineId", nullable = false)
    private Routine routine;

    private java.time.LocalDateTime executedAt;
}

