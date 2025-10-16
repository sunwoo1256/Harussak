package D.Co.Harussak.plant.controller;

import D.Co.Harussak.plant.dto.PlantDetailDto;
import D.Co.Harussak.plant.dto.PlantDto;
import D.Co.Harussak.plant.service.PlantDetailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity; // ResponseEntity 임포트
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "식물 상세 정보", description = "식물 상세 정보 관련 API")
@RestController
@RequestMapping("/users/plantinfo")
@RequiredArgsConstructor
public class PlantDetailController {
    private final PlantDetailService plantDetailService;

    @Operation(summary = "식물 목록 조회", description = "모든 식물 목록을 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @GetMapping
    public ResponseEntity<List<PlantDto>> getPlantList() {
        // 1. 서비스에서 데이터를 조회합니다.
        List<PlantDto> plantList = plantDetailService.getAllPlants();
        // 2. ResponseEntity.ok()를 사용하여 200 OK 상태 코드와 함께 데이터를 반환합니다.
        return ResponseEntity.ok(plantList);
    }

    @Operation(summary = "식물 상세 정보 조회", description = "ID를 이용하여 특정 식물의 상세 정보를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "404", description = "식물을 찾을 수 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PlantDetailDto> getPlantDetail(@PathVariable Long id) {
        // 1. 서비스에서 데이터를 조회합니다. (Optional 등으로 null 처리를 하는 것이 좋습니다)
        PlantDetailDto plantDetail = plantDetailService.getPlantDetail(id);

        // 2. 데이터 유무에 따라 다른 상태 코드를 반환합니다.
        if (plantDetail != null) {
            // 2-1. 데이터가 있으면 200 OK와 함께 데이터를 반환합니다.
            return ResponseEntity.ok(plantDetail);
        } else {
            // 2-2. 데이터가 없으면 404 Not Found 상태 코드를 반환합니다.
            return ResponseEntity.notFound().build();
        }
    }
}