package D.Co.Harussak.plant.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;

// Getter와 모든 필드를 포함하는 생성자를 Lombok으로 자동 생성합니다.
@Getter
@AllArgsConstructor
public class AiRoutineResponseDto {
    // 생성된 루틴 목록을 담을 필드
    private List<String> routines;
}