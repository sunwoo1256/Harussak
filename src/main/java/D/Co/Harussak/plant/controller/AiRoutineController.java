package D.Co.Harussak.plant.controller;

import D.Co.Harussak.entity.Plant;
import D.Co.Harussak.plant.dto.FlowerInfo;
import D.Co.Harussak.plant.dto.RoutineResponse;
import D.Co.Harussak.plant.repository.PlantRepository;
import D.Co.Harussak.plant.service.AiRoutineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users/ai") // URL 경로를 명확하게 변경하는 것을 추천합니다.
@RequiredArgsConstructor
public class AiRoutineController {

    private final AiRoutineService aiRoutineService;
    private final PlantRepository plantRepository;

    @PostMapping("/generate-routine")
    public ResponseEntity<RoutineResponse> generateAiRoutine(@RequestBody Map<String, String> request) {
        String userMood = request.get("mood");

        // 1. DB에서 모든 꽃 정보를 가져옵니다.
        List<Plant> allPlants = plantRepository.findAll();

        // 2. Plant Entity를 FlowerInfo DTO 리스트로 변환합니다.
        List<FlowerInfo> flowerInfoList = allPlants.stream()
                .map(plant -> new FlowerInfo(plant.getName(), plant.getFlowerMeaning()))
                .collect(Collectors.toList());

        // 3. 올바른 서비스 메서드를 호출합니다.
        RoutineResponse response = aiRoutineService.generateRoutinesAndFlower(userMood, flowerInfoList);

        // 4. 결과를 클라이언트에 반환합니다.
        return ResponseEntity.ok(response);
    }
}