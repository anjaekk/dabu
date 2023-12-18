package b172.challenging.gathering.dto;

import b172.challenging.gathering.domain.GatheringStatus;
import lombok.Builder;

@Builder
public record GatheringStatusResponseDto(
        String title,
        int remainNum,
        GatheringStatus gatheringStatus
) { }
