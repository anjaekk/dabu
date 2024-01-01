package b172.challenging.badge.dto.response;

import lombok.Builder;

@Builder
public record BadgeResponseDto(
        Long id,
        String name,
        String description,
        String imageUrl,
        boolean hasBadge
) {
}
