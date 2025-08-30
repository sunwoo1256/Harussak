package D.Co.Harussak.plant.controller;

import D.Co.Harussak.plant.dto.PlantCollectionDto;
import D.Co.Harussak.plant.dto.PlantDto;
import D.Co.Harussak.plant.service.PlantService;
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
    public PlantCollectionDto getPlantCollection() {
        List<PlantDto> plantList = plantService.getAllPlants();
        return new PlantCollectionDto(plantList);
    }


}
