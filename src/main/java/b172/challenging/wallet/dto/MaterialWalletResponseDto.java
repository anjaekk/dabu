package b172.challenging.wallet.dto;

import b172.challenging.wallet.domain.MaterialWallet;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Builder
@Schema(description = "모은 자재 리스트 결과")
public record MaterialWalletResponseDto(
        @Schema(description = "모은 자재 리스트")
        List<MaterialWallet> materialWallet
) {
}
