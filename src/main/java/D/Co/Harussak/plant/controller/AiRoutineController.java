package D.Co.Harussak.plant.controller;

import D.Co.Harussak.plant.service.AiRoutineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ai")
public class AiRoutineController {

    private final AiRoutineService aiRoutineService;

    public AiRoutineController(AiRoutineService aiRoutineService) {
        this.aiRoutineService = aiRoutineService;
    }

    @PostMapping("/routines")
    public ResponseEntity<List<String>> recommendRoutines(@RequestParam("userMood") String userMood) {
        try {
            List<String> routines = aiRoutineService.generateRoutines(userMood);
            return ResponseEntity.ok(routines);
        } catch (Exception e) {
            // 실제 프로덕션에서는 더 정교한 예외 처리가 필요합니다.
            return ResponseEntity.internalServerError().body(List.of("루틴 생성에 실패했습니다: " + e.getMessage()));
        }
    }
}