package D.Co.Harussak.plant.controller;

import D.Co.Harussak.entity.Plant;
import D.Co.Harussak.plant.dto.*;
import D.Co.Harussak.plant.repository.PlantRepository;
import D.Co.Harussak.plant.service.AiRoutineService;
import D.Co.Harussak.plant.service.PlantRoutineService;
import D.Co.Harussak.security.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "식물 루틴", description = "식물 루틴 관련 API")
@RestController
@RequestMapping("/users/plantRoutine")
@RequiredArgsConstructor
public class PlantRoutineController {

    private final PlantRoutineService plantRoutineService;
    private final AiRoutineService aiRoutineService;
    private final PlantRepository plantRepository;

    @Operation(summary = "AI 루틴 생성", description = "사용자의 기분에 따라 AI가 루틴과 추천 꽃을 반환합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "루틴 생성 성공"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PostMapping("/generate-ai-routine")
    public ResponseEntity<RoutineResponse> generateAiRoutine(
        @RequestBody AiRoutineRequestDto requestDto) {
        // 1. 요청 DTO에서 사용자의 기분(mood)을 추출합니다.
        String userMood = requestDto.getUserMood();

        // 2. DB에서 모든 식물(꽃) 정보를 조회합니다.
        List<Plant> allPlants = plantRepository.findAll();

        // 3. AI 서비스에 전달할 형태로 데이터를 가공합니다. (Plant Entity -> FlowerInfo DTO)
        List<FlowerInfo> flowerInfoList = allPlants.stream()
                .map(plant -> new FlowerInfo(plant.getId(), plant.getName(), plant.getFlowerMeaning()))
                .collect(Collectors.toList());

        // 4. AI 서비스를 호출하여 루틴과 추천 꽃 정보를 받아옵니다.
        // AiRoutineService의 반환 타입도 AiRoutineResponseDto로 맞춰주어야 합니다.
        RoutineResponse response = aiRoutineService.generateRoutinesAndFlower(userMood, flowerInfoList);

        // 5. 생성된 최종 응답 DTO를 클라이언트에 반환합니다.
        return ResponseEntity.ok(response);
    }

//    // --- 기존의 createRoutine, getRoutines 메서드는 그대로 유지합니다. ---
//    @Operation(summary = "식물 루틴 생성", description = "새로운 식물 루틴을 생성합니다.")
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "루틴 생성 성공"),
//            @ApiResponse(responseCode = "500", description = "서버 오류")
//    })
//    @PostMapping
//    public PlantRoutineDto createRoutine(@RequestBody PlantRoutineDto dto) {
//        return plantRoutineService.createRoutine(dto);
//    }
//
//    @Operation(summary = "사용자 식물 루틴 조회", description = "특정 사용자의 모든 식물 루틴을 조회합니다.")
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "조회 성공"),
//            @ApiResponse(responseCode = "500", description = "서버 오류")
//    })
//    @GetMapping("/{userId}")
//    public List<PlantRoutineDto> getRoutines(@PathVariable Long userId) {
//        return plantRoutineService.getUserRoutines(userId);
//    }
}