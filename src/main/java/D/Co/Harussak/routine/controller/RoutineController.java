package D.Co.Harussak.routine.controller;

import D.Co.Harussak.routine.dto.RoutineCreateRequest;
import D.Co.Harussak.routine.dto.RoutineResponse;
import D.Co.Harussak.routine.service.RoutineService;
import D.Co.Harussak.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
