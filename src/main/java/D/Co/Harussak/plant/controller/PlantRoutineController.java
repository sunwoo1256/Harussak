package D.Co.Harussak.plant.controller;

import D.Co.Harussak.entity.Plant;
import D.Co.Harussak.plant.dto.FlowerInfo;
import D.Co.Harussak.plant.dto.PlantRoutineDto;
import D.Co.Harussak.plant.dto.RoutineResponse;
import D.Co.Harussak.plant.repository.PlantRepository;
import D.Co.Harussak.plant.service.AiRoutineService;
import D.Co.Harussak.plant.service.PlantRoutineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/users/plantRoutine")
@RequiredArgsConstructor
public class PlantRoutineController {

    private final PlantRoutineService plantRoutineService;
    private final AiRoutineService aiRoutineService;
    private final PlantRepository plantRepository;

    @PostMapping("/generate-ai-routine")
    public ResponseEntity<RoutineResponse> generateAiRoutine(@RequestBody Map<String, String> request) {
        String userMood = request.get("mood");

        // 1. DB에서 모든 꽃(5개) 정보를 가져옵니다.
        List<Plant> allPlants = plantRepository.findAll();

        // 2. Plant Entity를 FlowerInfo DTO로 변환합니다.
        List<FlowerInfo> flowerInfoList = allPlants.stream()
                .map(plant -> new FlowerInfo(plant.getName(), plant.getFlowerMeaning()))
                .collect(Collectors.toList());

        // 3. AI 서비스를 호출하여 루틴과 꽃을 추천받습니다.
        RoutineResponse response = aiRoutineService.generateRoutinesAndFlower(userMood, flowerInfoList);

        // 4. 결과를 클라이언트에 반환합니다.
        return ResponseEntity.ok(response);
    }

    // --- 기존의 createRoutine, getRoutines 메서드는 그대로 유지합니다. ---
    @PostMapping
    public PlantRoutineDto createRoutine(@RequestBody PlantRoutineDto dto) {
        return plantRoutineService.createRoutine(dto);
    }

    @GetMapping("/{userId}")
    public List<PlantRoutineDto> getRoutines(@PathVariable Long userId) {
        return plantRoutineService.getUserRoutines(userId);
    }
}