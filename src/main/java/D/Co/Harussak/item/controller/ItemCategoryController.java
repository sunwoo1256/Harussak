package D.Co.Harussak.item.controller;

import D.Co.Harussak.item.dto.ItemCategoryDto;
import D.Co.Harussak.item.service.ItemCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity; // ResponseEntity 임포트
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Tag(name = "아이템 카테고리", description = "아이템 카테고리 관련 API")
@RestController
public class ItemCategoryController {

    private final ItemCategoryService itemCategoryService;

    public ItemCategoryController(ItemCategoryService itemCategoryService) {
        this.itemCategoryService = itemCategoryService;
    }

    @Operation(summary = "아이템 카테고리 목록 조회", description = "모든 아이템 카테고리 목록을 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @GetMapping("/users/itemcategory")
    public ResponseEntity<List<ItemCategoryDto>> getItemCategories() {
        try {
            // 1. 서비스에서 카테고리 목록을 조회합니다.
            List<ItemCategoryDto> categories = itemCategoryService.getAllCategories();
            // 2. 200 OK 상태 코드와 함께 데이터를 반환합니다.
            return ResponseEntity.ok(categories);
        } catch (Exception e) {
            // 3. 예외 발생 시 500 Internal Server Error를 반환합니다.
            return ResponseEntity.internalServerError().build();
        }
    }
}