package D.Co.Harussak.routine.dto;

import D.Co.Harussak.entity.RoutineRepeatDay;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoutineCreateRequest {
    private String title; // "책 30분 읽기"
    private LocalDate startDate;
    private LocalDate endDate; // "2025-09-30"
    private Set<RoutineRepeatDay.RepeatDay> repeatDays;
    private Long plantId;
    private String emoji;
    private String userMood;
}
