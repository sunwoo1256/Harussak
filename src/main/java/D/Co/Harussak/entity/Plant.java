package D.Co.Harussak.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "Plant")
public class Plant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String breed;
    private String flowerMeaning;
    private String month;
    private String traits;

    @Lob
    private String flowerImage;
}
