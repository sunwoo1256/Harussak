package D.Co.Harussak.data;

import D.Co.Harussak.entity.Plant;
import D.Co.Harussak.plant.repository.PlantRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final PlantRepository plantRepository;

    @PostConstruct
    public void init() {
        if (plantRepository.count() > 0) return; // 중복 방지

        List<Plant> plants = List.of(
            createPlant("보라 수국", "범의귀과", "진심", "6월~7월", "'Hydrangea'는 그리스어의 hydor(물)과 angos(항아리)의 합성어로 화형이 물항아리를 닮았다고 해 붙여졌다.", "/images/purple_sugook.jpg"),
            createPlant("빨간 튤립", "백합과", "열정", "4월~5월", "16세기 후반 유럽에서 이색적인 모양이 관심을 모으며 귀족이나 대상인들 사이에서 크게 유행하며 튤립은 귀족의 상징이 되었다.", "/images/red_tulip.jpg"),
            createPlant("은방울 꽃", "백합과", "희망", "5월~6월", "유럽에서는 5월에 은방울 꽃으로 만든 꽃다발을 받으면 행운이 온다고 믿어서 가장 가까운 벗에게 은방울 꽃을 선물한다고 한다.", "/images/eunbangul.jpg"),
            createPlant("프리지아", "붓꽃과", "새로운 시작을 응원합니다", "4월~5월", "겨울철, 특히 졸업과 입학시즌의 대표적인 꽃으로서 밝은 화색과 달콤한 향기로 많은 사람의 사랑을 받는, 소박하면서도 그 매력을 물씬 풍기는 아름다운 꽃이다.", "/images/freesia.jpg"),
            createPlant("해바라기", "국화과", "성취, 자부심", "8월~9월", "꽃이 진 뒤 검게 익은 수과에는 고급지방산이 들어 있어서 식용유, 특히 성인병 방지에 좋다고 하며 한방에서는 줄기속을 약재로 이용하고 있다.", "/images/sunflower.jpg")

        );

        plantRepository.saveAll(plants);
        System.out.println("✅ Plant 데이터 초기화 완료!");
    }

    private Plant createPlant(String name, String breed, String flowerMeaning, String month, String traits, String imageUrl) {
        Plant plant = new Plant();
        plant.setName(name);
        plant.setBreed(breed);
        plant.setFlowerMeaning(flowerMeaning);
        plant.setMonth(month);
        plant.setTraits(traits);
        plant.setFlowerImage(imageUrl); // 이미지 경로 저장
        return plant;
    }
}

