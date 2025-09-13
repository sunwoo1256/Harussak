package D.Co.Harussak.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Routine")
public class Routine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    private String title;
    private java.time.LocalDateTime startDate;
    private java.time.LocalDateTime endDate;

    @OneToMany(mappedBy = "routine", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RoutineRepeatDay> repeatDays = new HashSet<>();

    // 연관관계 편의 메서드
    public void addRepeatDay(RoutineRepeatDay repeatDay) {
        repeatDays.add(repeatDay);
        repeatDay.setRoutine(this);
    }

    public enum RepeatCycle {
        매일, 매주, 격주
    }
}

