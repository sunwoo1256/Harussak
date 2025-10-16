package D.Co.Harussak.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
    private java.time.LocalDate startDate;
    private java.time.LocalDate endDate;

    private String diary;

    private Long level;

    public Cultivation(){}

    public Cultivation(User user, Plant plant, Routine routine, String diary, String emoji) {
        this.user = user;
        this.plant = plant;
        this.routine = routine;
        this.diary = diary;
        this.emoji = emoji;
        this.startDate = routine.getStartDate();
        this.endDate = routine.getEndDate();
        this.level = 1L;
    }
}

