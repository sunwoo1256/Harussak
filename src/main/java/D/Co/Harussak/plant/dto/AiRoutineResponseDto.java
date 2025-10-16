package D.Co.Harussak.plant.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;

/**
 * AI 루틴 생성에 대한 최종 응답을 담는 DTO입니다.
 * 생성된 루틴 목록과 추천 꽃 정보를 포함합니다.
 */
@Getter
@AllArgsConstructor
public class AiRoutineResponseDto {

    private List<String> routines;
    private FlowerInfo recommendedFlower;

}