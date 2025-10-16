package D.Co.Harussak.item.controller;

import D.Co.Harussak.item.dto.ItemDto;
import D.Co.Harussak.item.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity; // ResponseEntity 임포트
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "아이템", description = "아이템 관련 API")
@RestController
@RequestMapping("/users/{userId}/terrarium")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @Operation(summary = "상점 아이템 목록 조회", description = "상점에서 판매하는 모든 아이템 목록을 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @GetMapping("/shop")
    public ResponseEntity<List<ItemDto>> getShopItems() {
        try {
            List<ItemDto> shopItems = itemService.getAllShopItems();
            return ResponseEntity.ok(shopItems);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "아이템 구매", description = "사용자가 아이템을 구매합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "구매 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PutMapping
    public ResponseEntity<Map<String, String>> purchaseItems(
            @PathVariable Long userId,
            @RequestBody Map<String, List<ItemDto>> items
    ) {
        try {
            List<ItemDto> itemDtos = items.get("items");
            itemService.purchaseItems(userId, itemDtos);
            // 성공 시 200 OK 상태와 함께 성공 메시지를 담은 Map을 반환합니다.
            return ResponseEntity.ok(Map.of("result", "success"));
        } catch (IllegalArgumentException e) {
            // 특정 예외 (예: 잘못된 요청)에 대해서는 400 Bad Request를 반환할 수 있습니다.
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            // 그 외 일반적인 서버 오류는 500 Internal Server Error를 반환합니다.
            return ResponseEntity.internalServerError().body(Map.of("error", "An unexpected error occurred."));
        }
    }
}