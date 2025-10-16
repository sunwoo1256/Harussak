package D.Co.Harussak.plant.dto; // 혹은 원하시는 DTO 패키지 경로

import java.util.List;

/**
 * @param routines Getter 메서드
 */
public record RoutineResponse(List<String> routines, String recommendedFlower) {

}