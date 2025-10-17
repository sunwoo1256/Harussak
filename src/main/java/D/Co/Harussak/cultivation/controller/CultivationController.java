package D.Co.Harussak.cultivation.controller;

import D.Co.Harussak.cultivation.dto.CultivationRequestDto;
import D.Co.Harussak.cultivation.dto.CultivationResponseDto;
import D.Co.Harussak.cultivation.dto.CultivationUpdateDto;
import D.Co.Harussak.cultivation.service.CultivationService;
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

//    @Operation(summary = "재배 시작", description = "사용자가 새로운 식물 재배를 시작합니다.")
//    @PostMapping
//    public ResponseEntity<CultivationResponseDto> startCultivation(@RequestBody CultivationRequestDto requestDto) {
//        CultivationResponseDto responseDto = cultivationService.createCultivation(requestDto);
//        return ResponseEntity.ok(responseDto);
//    }

    @Operation(summary = "사용자의 재배 목록 조회", description = "특정 사용자의 모든 재배 정보를 조회합니다.")
    @GetMapping("/user")
    public ResponseEntity<List<CultivationResponseDto>> getUserCultivations(@AuthenticationPrincipal CustomUserDetails user) {
        List<CultivationResponseDto> responseDtos = cultivationService.getCultivationsByUserId(user.getUsername());
        return ResponseEntity.ok(responseDtos);
    }

//    @Operation(summary = "재배 정보 수정", description = "재배일지(diary)나 이모지를 수정합니다.")
//    @PatchMapping("/{cultivationId}")
//    public ResponseEntity<CultivationResponseDto> updateCultivation(
//        @PathVariable Long cultivationId,
//        @RequestBody CultivationUpdateDto updateDto) {
//        CultivationResponseDto responseDto = cultivationService.updateCultivation(cultivationId, updateDto);
//        return ResponseEntity.ok(responseDto);
//    }

    @Operation(summary = "재배 중단", description = "진행 중인 재배를 삭제합니다.")
    @DeleteMapping("/{cultivationId}")
    public ResponseEntity<Void> stopCultivation(@PathVariable Long cultivationId) {
        cultivationService.deleteCultivation(cultivationId);
        return ResponseEntity.noContent().build(); // Success with no content in response
    }
}
