package b172.challenging.gathering.domain;

import b172.challenging.auth.domain.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Table(name = "gathering",
    indexes = @Index(name = "idx_gathering_platform", columnList = "platform")
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Gathering {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gathering_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_member_id", nullable = false)
//    @JsonIgnore
    private Member ownerMember;

    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private AppTechPlatform platform;

    @Column(nullable = false)
    private String title;

    @Column(name = "people_num", nullable = false)
    private int peopleNum;

    @Column(name = "goal_amount", nullable = false)
    private Long goalAmount;

    @Column(name = "working_days", nullable = false)
    private int workingDays;

    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private GatheringStatus status;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "gathering", cascade = { CascadeType.PERSIST , CascadeType.MERGE })
    private List<GatheringMember> gatheringMembers;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public void addGatheringMember(GatheringMember gatheringMember){
        gatheringMembers.add(gatheringMember);
        gatheringMember.setGathering(this);
    }
}
