package b172.challenging.protip.dto;

import b172.challenging.protip.domain.ProTip;
import lombok.Builder;

@Builder
public record ProTipEditResponseDto (
        ProTip proTip
) { }
