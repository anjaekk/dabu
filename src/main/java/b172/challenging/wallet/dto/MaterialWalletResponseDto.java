package b172.challenging.wallet.dto;

import b172.challenging.wallet.domain.MaterialWallet;
import lombok.Builder;

@Builder
public record MaterialWalletResponseDto(
    MaterialWallet materialWallet
) {
}
