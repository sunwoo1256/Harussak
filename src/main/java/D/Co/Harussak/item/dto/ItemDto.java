package D.Co.Harussak.item.dto;
import lombok.Data;
@Data
public class ItemDto {
    private Long id;
    private String name;
    private String itemImage;
    private String category;
    private Long price;
    private Integer quantity; // 요청/응답에서 수량 포함

    // 기본 생성자, getters/setters
    public ItemDto() {}

    public ItemDto(Long id, String name, String itemImage, String category, Long price, Integer quantity) {
        this.id = id;
        this.name = name;
        this.itemImage = itemImage;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }

    // getters and setters...
}
