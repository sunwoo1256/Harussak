package D.Co.Harussak.item.controller;

import D.Co.Harussak.item.dto.ItemCategoryDto;
import D.Co.Harussak.item.service.ItemCategoryService;
import org.springframework.http.ResponseEntity; // ResponseEntity 임포트
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class ItemCategoryController {

    private final ItemCategoryService itemCategoryService;

    public ItemCategoryController(ItemCategoryService itemCategoryService) {
        this.itemCategoryService = itemCategoryService;
    }

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