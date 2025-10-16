package D.Co.Harussak.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemImage;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String name;
    private Long price;

    public enum Category {
        장식, 하늘
    }
}
