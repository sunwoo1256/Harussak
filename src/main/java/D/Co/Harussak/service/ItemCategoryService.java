package D.Co.Harussak.service;

import D.Co.Harussak.dto.ItemCategoryDto;
import D.Co.Harussak.entity.Item;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemCategoryService {

    private final EntityManager em;

    public ItemCategoryService(EntityManager em) {
        this.em = em;
    }

    public List<ItemCategoryDto> getAllCategories() {
        List<String> results = em.createQuery(
                "SELECT DISTINCT i.category FROM Item i", String.class
        ).getResultList();

        return results.stream()
                .map(Item.Category::valueOf)
                .sorted((c1, c2) -> c1.ordinal() - c2.ordinal())
                .map(c -> new ItemCategoryDto(
                        (long) (c.ordinal() + 1),
                        categoryToKorean(c)
                ))
                .collect(Collectors.toList());
    }

    private String categoryToKorean(Item.Category category) {
        if (category == null) return "";
        switch (category) {
            case 테라리움: return "화분";
            case 장식: return "장식";
            case 업그레이드: return "식물";
            default: return category.name();
        }
    }
}
