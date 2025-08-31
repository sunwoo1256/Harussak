package D.Co.Harussak.dto;

import D.Co.Harussak.entity.Routine;
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
    private Routine.RepeatDay repeatDay;
    private String seed;
    private String emotion;

    // getter/setter 생략
}
