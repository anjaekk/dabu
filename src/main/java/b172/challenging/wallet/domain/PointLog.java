package b172.challenging.wallet.domain;

import b172.challenging.auth.domain.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "point_log")
@NoArgsConstructor
public class PointLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "point_log_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(length = 10)
    @Enumerated(EnumType.STRING)
    private PointLogType type;

    @Column(name = "sub_type", length = 10)
    @Enumerated(EnumType.STRING)
    private PointLogSubType subType;

    @Column(nullable = false, columnDefinition = "bigint default 0")
    private Long point;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
