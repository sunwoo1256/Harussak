package D.Co.Harussak.service;

import D.Co.Harussak.dto.ItemDto;
import D.Co.Harussak.entity.Item;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemService {

    private final EntityManager em;

    public ItemService(EntityManager em) {
        this.em = em;
    }

    public List<ItemDto> getAllShopItems() {
        List<Item> items = em.createQuery("SELECT i FROM Item i", Item.class).getResultList();
        return items.stream()
                .map(i -> new ItemDto(
                        i.getId(),
                        i.getName(),
                        i.getItemImage(),
                        i.getCategory().name(),
                        i.getPrice(),
                        null // 상점에서 구매하기 전이므로 수량 null
                ))
                .collect(Collectors.toList());
    }

    @Transactional
    public void purchaseItems(Long userId, List<ItemDto> purchaseDtos) {
        for (ItemDto dto : purchaseDtos) {
            Item item = em.find(Item.class, dto.getId());
            // 여기서 구매 로직(인벤토리, 재화 차감 등) 구현
            // 예시: 인벤토리 엔티티 생성
            // ...구현 생략
        }
    }
}
