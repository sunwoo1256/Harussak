package D.Co.Harussak.plant.controller;

import D.Co.Harussak.plant.dto.PlantDetailDto;
import D.Co.Harussak.plant.dto.PlantDto;
import D.Co.Harussak.plant.service.PlantDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity; // ResponseEntity 임포트
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users/plantinfo")
@RequiredArgsConstructor
public class PlantDetailController {
    private final PlantDetailService plantDetailService;

    @GetMapping
    public ResponseEntity<List<PlantDto>> getPlantList() {
        // 1. 서비스에서 데이터를 조회합니다.
        List<PlantDto> plantList = plantDetailService.getAllPlants();
        // 2. ResponseEntity.ok()를 사용하여 200 OK 상태 코드와 함께 데이터를 반환합니다.
        return ResponseEntity.ok(plantList);
    }

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