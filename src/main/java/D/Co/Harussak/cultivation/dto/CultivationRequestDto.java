package D.Co.Harussak.cultivation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CultivationRequestDto {
    private Long userId;
    private Long plantId;
    private Long routineId;
    private String emoji; // ex: "😊"
}
