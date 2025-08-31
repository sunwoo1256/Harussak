package D.Co.Harussak.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Routine")
public class Routine {
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    private String title;
    private java.time.LocalDateTime startDate;
    private java.time.LocalDateTime endDate;

    @Enumerated(EnumType.STRING)
    private RepeatCycle repeatCycle;

    @Enumerated(EnumType.STRING)
    private RepeatDay repeatDay;

    public enum RepeatCycle {
        매일, 매주, 격주
    }

    public enum RepeatDay {
        월, 화, 수, 목, 금, 토, 일
    }
}

