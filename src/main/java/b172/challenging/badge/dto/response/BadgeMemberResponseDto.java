package b172.challenging.badge.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record BadgeMemberResponseDto(

        List<BadgeResponseDto> badges
) {
}
