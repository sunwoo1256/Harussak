//package D.Co.Harussak.plant.controller;
//
//import D.Co.Harussak.plant.dto.PlantCollectionDto;
//import D.Co.Harussak.plant.dto.PlantDto;
//import D.Co.Harussak.plant.service.PlantService;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import org.springframework.http.ResponseEntity; // ResponseEntity 임포트
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@Tag(name = "식물 도감", description = "식물 도감 관련 API")
//@RestController
//public class PlantCollectionController {
//
//    private final PlantService plantService;
//
//    public PlantCollectionController(PlantService plantService) {
//        this.plantService = plantService;
//    }
//
//    @Operation(summary = "식물 도감 조회", description = "모든 식물 목록을 조회하여 식물 도감을 생성합니다.")
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "조회 성공"),
//            @ApiResponse(responseCode = "500", description = "서버 오류")
//    })
//    @GetMapping("/users/plantcollection")
//    public ResponseEntity<PlantCollectionDto> getPlantCollection() {
//        // 1. 서비스에서 데이터를 조회합니다.
//        List<PlantDto> plantList = plantService.getAllPlants();
//        PlantCollectionDto collection = new PlantCollectionDto(plantList);
//        // 2. 200 OK 상태 코드와 함께 DTO를 반환합니다.
//        return ResponseEntity.ok(collection);
//    }
//}