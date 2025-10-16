//package D.Co.Harussak.plant.controller;
//
//import D.Co.Harussak.entity.Plant;
//import D.Co.Harussak.plant.dto.AiRoutineRequestDto;
//import D.Co.Harussak.plant.dto.FlowerInfo;
//import D.Co.Harussak.plant.dto.RoutineResponse;
//import D.Co.Harussak.plant.repository.PlantRepository;
//import D.Co.Harussak.plant.service.AiRoutineService;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Tag(name = "AI 루틴", description = "AI 루틴 관련 API")
//@RestController
//@RequestMapping("/users/ai")
//@RequiredArgsConstructor
//public class AiRoutineController {
//
//    private final AiRoutineService aiRoutineService;
//    private final PlantRepository plantRepository;
//
//    @Operation(summary = "AI 루틴 생성", description = "사용자의 기분에 따라 AI가 루틴과 추천 꽃을 반환합니다.")
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "루틴 생성 성공"),
//            @ApiResponse(responseCode = "500", description = "서버 오류")
//    })
//    @PostMapping("/generate-routine")
//    public ResponseEntity<RoutineResponse> generateAiRoutine(@RequestBody AiRoutineRequestDto request) {
//        // 1. 요청 DTO에서 사용자의 기분(mood)을 추출합니다.
//        String userMood = request.getMood();
//
//        // 2. DB에서 모든 식물(꽃) 정보를 조회합니다.
//        List<Plant> allPlants = plantRepository.findAll();
//
//        // 3. AI 서비스에 전달할 형태로 데이터를 가공합니다. (Plant Entity -> FlowerInfo DTO)
//        List<FlowerInfo> flowerInfoList = allPlants.stream()
//                .map(plant -> new FlowerInfo(plant.getName(), plant.getFlowerMeaning()))
//                .collect(Collectors.toList());
//
//        // 4. AI 서비스에 사용자의 기분과 꽃 목록을 전달하여 루틴과 추천 꽃 정보를 받아옵니다.
//        // 서비스의 반환 타입이 AiRoutineResponseDto 여야 합니다.
//        RoutineResponse responseDto = aiRoutineService.generateRoutinesAndFlower(userMood, flowerInfoList);
//
//        // 5. 생성된 최종 응답 DTO를 클라이언트에 반환합니다.
//        return ResponseEntity.ok(responseDto);
//    }
//}