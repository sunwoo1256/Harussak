package D.Co.Harussak.cultivation.dto;

import D.Co.Harussak.entity.Cultivation;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor; // NoArgsConstructor 임포트
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor // 모든 필드를 받는 생성자 대신 기본 생성자를 추가합니다.
public class CultivationResponseDto {
    private Long id;
    private Long userId;
    private Long plantId;
    private String plantName; // For easy display
    private Long routineId;
    private String routineTitle; // For easy display
    private String emoji;
    private LocalDate startDate;
    private LocalDate endDate;
    private String diary;
    private Long level;
    private String imageUrl;

    // Entity to DTO mapping constructor (이 생성자는 그대로 유지합니다)
    public CultivationResponseDto(Cultivation cultivation) {
        this.id = cultivation.getId();
        this.userId = cultivation.getUser().getId();
        this.plantId = cultivation.getPlant().getId();
        this.plantName = cultivation.getPlant().getName();
        this.routineId = cultivation.getRoutine().getId();
        this.routineTitle = cultivation.getRoutine().getTitle();
        this.emoji = cultivation.getEmoji();
        this.startDate = cultivation.getStartDate();
        this.endDate = cultivation.getEndDate();
        this.diary = cultivation.getDiary();
        this.level = cultivation.getLevel();
        this.imageUrl = cultivation.getCultivationObject().getImageUrl();
    }
}
