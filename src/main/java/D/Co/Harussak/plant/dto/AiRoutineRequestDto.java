package D.Co.Harussak.plant.dto;

import lombok.Getter;
import lombok.Setter;

// Getter와 Setter를 Lombok으로 자동 생성합니다.
@Getter
@Setter
public class AiRoutineRequestDto {
    // 사용자의 기분을 담을 필드
    private String userMood;
}