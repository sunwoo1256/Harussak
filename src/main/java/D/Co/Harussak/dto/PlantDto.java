package D.Co.Harussak.dto;

import lombok.Data;

@Data

public class PlantDto {
    private Long plantId;
    private String name;

    public PlantDto(Long plantId, String name) {
        this.plantId = plantId;
        this.name = name;
    }

}
