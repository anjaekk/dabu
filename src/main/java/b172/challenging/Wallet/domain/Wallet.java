package b172.challenging.Wallet.domain;

import b172.challenging.Home.domain.Home;
import b172.challenging.auth.domain.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "wallet")
@NoArgsConstructor
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wallet_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_id", nullable = true) // FIXME: home 정보 추가 후 nullable=false로 변경
    private Home home;

    @Column(name = "home_name", length = 30)
    private String homeName;

    @Column(nullable = false, columnDefinition = "bigint default 0")
    private Long point;

    @Column(name = "save_amount", nullable = false, columnDefinition = "bigint default 0")
    private Long saveAmount;

    @Column(name = "home_updated_at")
    private LocalDateTime homeUpdatedAt;

}
