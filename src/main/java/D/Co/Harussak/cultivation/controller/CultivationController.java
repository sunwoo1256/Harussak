package D.Co.Harussak.cultivation.controller;

import D.Co.Harussak.cultivation.dto.CultivationRequestDto;
import D.Co.Harussak.cultivation.dto.CultivationResponseDto;
import D.Co.Harussak.cultivation.dto.CultivationUpdateDto;
import D.Co.Harussak.cultivation.service.CultivationService;
import D.Co.Harussak.routine.service.RoutineService;
import D.Co.Harussak.security.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "재배", description = "식물 재배(키우기) 관련 API")
@RestController
@RequestMapping("/cultivations")
@RequiredArgsConstructor
public class CultivationController {

    private final CultivationService cultivationService;


    @Operation(summary = "사용자의 재배 목록 조회", description = "특정 사용자의 모든 재배 정보를 조회합니다.")
    @GetMapping("/user")
    public ResponseEntity<List<CultivationResponseDto>> getUserCultivations(@AuthenticationPrincipal CustomUserDetails user) {
        List<CultivationResponseDto> responseDtos = cultivationService.getCultivationsByUserId(user.getUsername());
        return ResponseEntity.ok(responseDtos);
    }

}
