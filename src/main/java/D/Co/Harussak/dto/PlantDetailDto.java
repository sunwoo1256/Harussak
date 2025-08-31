package D.Co.Harussak.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlantDetailDto {
    private Long plantId;
    private String name;
    private String breed;
    private String flowerMeaning;
    private String month;
    private String traits;
    private String flowerImage;
}
