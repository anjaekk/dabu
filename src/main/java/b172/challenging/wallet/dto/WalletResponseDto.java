package b172.challenging.wallet.dto;

import b172.challenging.auth.domain.Member;
import b172.challenging.myhome.domain.MyHome;
import b172.challenging.wallet.domain.MaterialWallet;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record WalletResponseDto(
        Long id,
        Member member,
        MyHome myHome,
        String myHomeName,
        Long point,
        Long saveAmount,
        LocalDateTime homeUpdatedAt
) {
}
