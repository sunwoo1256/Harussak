package D.Co.Harussak.plant.controller;

import D.Co.Harussak.plant.dto.PlantRoutineDto;
import D.Co.Harussak.plant.service.PlantRoutineService;
import org.springframework.http.HttpStatus; // HttpStatus 임포트
import org.springframework.http.ResponseEntity; // ResponseEntity 임포트
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
    public ResponseEntity<PlantRoutineDto> createRoutine(@RequestBody PlantRoutineDto dto) {
        // 1. 서비스를 통해 루틴을 생성합니다.
        PlantRoutineDto createdRoutine = plantRoutineService.createRoutine(dto);
        // 2. 201 Created 상태 코드와 함께 생성된 데이터를 반환합니다.
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRoutine);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<PlantRoutineDto>> getRoutines(@PathVariable Long userId) {
        // 1. 서비스에서 데이터를 조회합니다.
        List<PlantRoutineDto> routines = plantRoutineService.getUserRoutines(userId);
        // 2. 200 OK 상태 코드와 함께 데이터를 반환합니다.
        return ResponseEntity.ok(routines);
    }
}