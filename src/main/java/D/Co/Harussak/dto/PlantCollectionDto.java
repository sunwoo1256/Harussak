package D.Co.Harussak.dto;

import lombok.Data;
import java.util.List;

@Data
public class PlantCollectionDto {
    private List<PlantDto> plants;

    public PlantCollectionDto(List<PlantDto> plants) {
        this.plants = plants;
    }
}
