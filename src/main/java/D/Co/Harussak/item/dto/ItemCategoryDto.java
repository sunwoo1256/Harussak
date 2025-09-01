package D.Co.Harussak.item.dto;
import lombok.Data;

@Data
public class ItemCategoryDto {
    private Long id;
    private String name;

    // 두 개의 인수를 받는 생성자 직접 추가!
    public ItemCategoryDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
