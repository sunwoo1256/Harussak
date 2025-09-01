package D.Co.Harussak.item.controller;

import D.Co.Harussak.item.dto.ItemCategoryDto;
import D.Co.Harussak.item.service.ItemCategoryService;
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
    public List<ItemCategoryDto> getItemCategories() {
        return itemCategoryService.getAllCategories();
    }
}
