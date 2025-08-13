package D.Co.Harussak.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Plant")
public class Plant {
    @Id
    private Long id;

    private String breed;
    private String flowerMeaning;
    private String month;
    private String traits;

    @Lob
    private String flowerImage;
}
