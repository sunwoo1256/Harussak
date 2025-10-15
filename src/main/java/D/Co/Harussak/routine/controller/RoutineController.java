package D.Co.Harussak.routine.controller;

import D.Co.Harussak.entity.RoutineLog;
import D.Co.Harussak.routine.dto.RoutineCreateRequest;
import D.Co.Harussak.routine.dto.RoutineDto;
import D.Co.Harussak.routine.dto.RoutineResponse;
import D.Co.Harussak.routine.service.RoutineService;
import D.Co.Harussak.security.CustomUserDetails;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/routines")
@RequiredArgsConstructor
public class RoutineController {

    private final RoutineService routineService;

    @PostMapping
    public ResponseEntity<RoutineResponse> createRoutine(
        @AuthenticationPrincipal CustomUserDetails userDetails,
        @RequestBody RoutineCreateRequest request
    ) {
        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        RoutineResponse response = routineService.createRoutine(userDetails.getUsername(), request);
        return ResponseEntity.ok(response);
    }

    // 특정 날짜별 루틴 조회
    @GetMapping("/by-date/{date}")
    public ResponseEntity<List<RoutineDto>> getRoutinesByDate(
        @AuthenticationPrincipal CustomUserDetails user,
        @PathVariable LocalDate date
    ) {
        return ResponseEntity.ok(routineService.getRoutinesByDate(user.getUsername(), date));
    }

    // 루틴 완료/취소 토글
    @PatchMapping("/{routineId}/complete")
    public ResponseEntity<Void> toggleRoutine(
        @AuthenticationPrincipal CustomUserDetails user,
        @PathVariable Long routineId
    ) {
        routineService.toggleRoutineLog(routineId, user.getUsername());
        return ResponseEntity.ok().build();
    }
}
