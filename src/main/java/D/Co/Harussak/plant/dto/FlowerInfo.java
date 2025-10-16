package D.Co.Harussak.plant.dto;

import lombok.Getter;

@Getter
public class FlowerInfo {
    private final Long id;
    private final String flowerName;
    private final String flowerMeaning;

    public FlowerInfo(Long id, String flowerName, String flowerMeaning) {
        this.id = id;
        this.flowerName = flowerName;
        this.flowerMeaning = flowerMeaning;
    }

}