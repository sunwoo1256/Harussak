package D.Co.Harussak.routine.controller;

import D.Co.Harussak.entity.RoutineLog;
import D.Co.Harussak.routine.dto.RoutineCreateRequest;
import D.Co.Harussak.routine.dto.RoutineDto;
import D.Co.Harussak.routine.dto.RoutineResponse;
import D.Co.Harussak.routine.service.RoutineService;
import D.Co.Harussak.security.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "루틴", description = "루틴 관련 API")
@RestController
@RequestMapping("/routines")
@RequiredArgsConstructor
public class RoutineController {

    private final RoutineService routineService;

    @Operation(summary = "루틴 생성", description = "새로운 루틴을 생성합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "루틴 생성 성공"),
            @ApiResponse(responseCode = "401", description = "인증 실패"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
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

    @Operation(summary = "날짜별 루틴 조회", description = "특정 날짜의 루틴 목록을 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @GetMapping("/by-date/{date}")
    public ResponseEntity<List<RoutineDto>> getRoutinesByDate(
        @AuthenticationPrincipal CustomUserDetails user,
        @PathVariable LocalDate date
    ) {
        return ResponseEntity.ok(routineService.getRoutinesByDate(user.getUsername(), date));
    }

    @Operation(summary = "루틴 완료/취소 토글", description = "루틴의 완료 상태를 토글합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "토글 성공"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PatchMapping("/{routineId}/complete")
    public ResponseEntity<Void> toggleRoutine(
        @AuthenticationPrincipal CustomUserDetails user,
        @PathVariable Long routineId
    ) {
        routineService.toggleRoutineLog(routineId, user.getUsername());
        return ResponseEntity.ok().build();
    }
}