package D.Co.Harussak.plant.dto; // 혹은 원하시는 DTO 패키지 경로

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class RoutineResponse{
    List<String> routines;
    Long flowerId;
    String recommendedFlower;
    String userMood;
}