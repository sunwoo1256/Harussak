package D.Co.Harussak.controller;

import D.Co.Harussak.dto.ItemDto;
import D.Co.Harussak.service.ItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users/{userId}/terrarium")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/shop")
    public List<ItemDto> getShopItems() {
        return itemService.getAllShopItems();
    }

    @PutMapping
    public Map<String, String> purchaseItems(
            @PathVariable Long userId,
            @RequestBody Map<String, List<ItemDto>> items
    ) {
        List<ItemDto> itemDtos = items.get("items");
        itemService.purchaseItems(userId, itemDtos);
        return Map.of("result", "success");
    }
}
