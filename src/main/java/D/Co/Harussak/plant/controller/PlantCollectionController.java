package D.Co.Harussak.plant.controller;

import D.Co.Harussak.plant.dto.PlantCollectionDto;
import D.Co.Harussak.plant.dto.PlantDto;
import D.Co.Harussak.plant.service.PlantService;
import org.springframework.http.ResponseEntity; // ResponseEntity 임포트
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlantCollectionController {

    private final PlantService plantService;

    public PlantCollectionController(PlantService plantService) {
        this.plantService = plantService;
    }

    @GetMapping("/users/plantcollection")
    public ResponseEntity<PlantCollectionDto> getPlantCollection() {
        // 1. 서비스에서 데이터를 조회합니다.
        List<PlantDto> plantList = plantService.getAllPlants();
        PlantCollectionDto collection = new PlantCollectionDto(plantList);
        // 2. 200 OK 상태 코드와 함께 DTO를 반환합니다.
        return ResponseEntity.ok(collection);
    }
}