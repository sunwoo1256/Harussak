package D.Co.Harussak.plant.dto;

import lombok.Getter;

@Getter
public class FlowerInfo {
    private final String flowerName;
    private final String flowerMeaning;

    public FlowerInfo(String flowerName, String flowerMeaning) {
        this.flowerName = flowerName;
        this.flowerMeaning = flowerMeaning;
    }

}