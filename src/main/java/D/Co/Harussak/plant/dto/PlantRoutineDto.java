package D.Co.Harussak.plant.dto;

import D.Co.Harussak.entity.Routine;
import D.Co.Harussak.entity.RoutineRepeatDay;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter
public class PlantRoutineDto {
    private Long id;
    private Long userId;
    private String title;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Routine.RepeatCycle repeatCycle;
    private RoutineRepeatDay.RepeatDay repeatDay;
    private String seed;
    private String emotion;

    // getter/setter 생략
}
