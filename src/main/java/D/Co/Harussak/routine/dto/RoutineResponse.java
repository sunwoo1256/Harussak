package D.Co.Harussak.routine.dto;

import D.Co.Harussak.entity.RoutineRepeatDay;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class RoutineResponse {
    private Long id;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private Set<RoutineRepeatDay.RepeatDay> repeatDays;
    private String breed;
}
