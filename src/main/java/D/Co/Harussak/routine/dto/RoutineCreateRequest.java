package D.Co.Harussak.routine.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoutineCreateRequest {
    private String routine; // "책 30분 읽기"
    private LocalDate endDate; // "2025-09-30"
    private List<String> repeatDays; // ["월", "수", "금"]
}
