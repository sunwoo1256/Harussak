package D.Co.Harussak.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "RoutineLog")
public class RoutineLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "routine_id")
    private Routine routine;

    private LocalDate date;
    private boolean completed;

    public RoutineLog(Routine routine, LocalDate date) {
        this.routine = routine;
        this.date = date;
        this.completed = false;
    }

    public void toggle() {
        this.completed = !this.completed;
    }
}

