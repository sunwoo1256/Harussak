package D.Co.Harussak.item.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

/**
 * 아이템 구매 요청을 처리하기 위한 DTO입니다.
 * 사용자가 구매하려는 아이템 목록을 담습니다.
 */
@Getter
@Setter
public class PurchaseRequestDto {
    private List<ItemDto> items;
}