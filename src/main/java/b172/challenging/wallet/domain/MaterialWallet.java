package b172.challenging.wallet.domain;

import b172.challenging.myhome.domain.HomeMaterial;
import b172.challenging.auth.domain.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "material_wallet")
@NoArgsConstructor
public class MaterialWallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "material_wallet_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "home_material_id", nullable = false)
    private HomeMaterial homeMaterial;

    @Column(name = "collected", nullable = false, columnDefinition = "bigint default 0")
    private Long collected;
}
