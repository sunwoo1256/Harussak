package D.Co.Harussak.controller;

import D.Co.Harussak.dto.PlantRoutineDto;
import D.Co.Harussak.service.PlantRoutineService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/plantRoutine")
public class PlantRoutineController {
    private final PlantRoutineService plantRoutineService;

    public PlantRoutineController(PlantRoutineService plantRoutineService) {
        this.plantRoutineService = plantRoutineService;
    }

    @PostMapping
    public PlantRoutineDto createRoutine(@RequestBody PlantRoutineDto dto) {
        return plantRoutineService.createRoutine(dto);
    }

    @GetMapping("/{userId}")
    public List<PlantRoutineDto> getRoutines(@PathVariable Long userId) {
        return plantRoutineService.getUserRoutines(userId);
    }
}
