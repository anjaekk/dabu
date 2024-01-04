package b172.challenging.badge.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "badge")
@NoArgsConstructor
@Schema(description = "배지 정보")
public class Badge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "badge_id")
    private Long id;

    @Column(nullable = false, length = 255)
    @Schema(description = "관리용 배지 이름")
    private String slug;

    @Column(nullable = false, length = 255)
    @Schema(description = "사용자 화면 배지 이름")
    private String name;

    @Column(nullable = false, length = 255)
    @Schema(description = "배지 획득 방법")
    private String description;

    @Column(name = "image_url", nullable = false, length = 255)
    @Schema(description = "기본(미보유) 배지 이미지 url")
    private String imageUrl;

    @Column(name = "has_image_url", nullable = false, length = 255)
    @Schema(description = "사용자 보유 배지 이미지 url")
    private String hasImageUrl;

    @Column(name = "created_at", nullable = false, updatable = false)
    @Schema(description = "생성 일")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @Schema(description = "수정 일")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}