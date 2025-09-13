package D.Co.Harussak.routine.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RoutineResponse {
    private Long id;
    private String routine;
    private LocalDate endDate;
    private List<String> repeatDays;
    private LocalDateTime createdAt;
}
