package D.Co.Harussak.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Setting")
public class Setting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    private Boolean pushAlarm;
    private String appVersion;
    private Boolean alarmSound;
    private Boolean vibration;
    private Boolean effectSound;
    private Boolean bgm;
}

