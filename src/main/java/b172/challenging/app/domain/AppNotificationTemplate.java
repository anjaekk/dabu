package b172.challenging.app.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "app_notification_template")
@NoArgsConstructor
public class AppNotificationTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "app_notification_template_id")
    private Long id;

    @Column(nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private AppNotificationType type;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;
}
