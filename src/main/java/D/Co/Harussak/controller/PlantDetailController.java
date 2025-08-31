package D.Co.Harussak.controller;

import D.Co.Harussak.dto.PlantDto;
import D.Co.Harussak.dto.PlantDetailDto;
import D.Co.Harussak.service.PlantDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/plantinfo")
@RequiredArgsConstructor
public class PlantDetailController {
    private final PlantDetailService plantDetailService;

    @GetMapping
    public List<PlantDto> getPlantList() {
        return plantDetailService.getAllPlants();
    }

    @GetMapping("/{id}")
    public PlantDetailDto getPlantDetail(@PathVariable Long id) {
        return plantDetailService.getPlantDetail(id);
    }
}
