package D.Co.Harussak.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Item")
public class Item {
    @Id
    private Long id;

    @Lob
    private String itemImage;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String name;
    private Long price;

    public enum Category {
        테라리움, 장식, 업그레이드
    }
}
