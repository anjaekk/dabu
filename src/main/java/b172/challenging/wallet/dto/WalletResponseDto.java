package b172.challenging.wallet.dto;

import b172.challenging.auth.domain.Member;
import b172.challenging.myhome.domain.MyHome;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
@Schema(description = "사용자 포인트 조회")
public record WalletResponseDto(
        Long id,
        Member member,
        MyHome myHome,
        String myHomeName,
        @Schema(description = "현재 포인트")
        Long point,
        @Schema(description = "전체 모은 양")
        Long saveAmount,
        LocalDateTime homeUpdatedAt
) {
}
