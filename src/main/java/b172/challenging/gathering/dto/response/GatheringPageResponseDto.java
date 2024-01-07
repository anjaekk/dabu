package b172.challenging.gathering.dto.response;

import b172.challenging.gathering.domain.AppTechPlatform;
import b172.challenging.gathering.domain.Gathering;
import b172.challenging.gathering.domain.GatheringMember;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record GatheringPageResponseDto(
        List<Gathering> gatherings,
        int pageNo,
        int pageSize,
        long totalElements,
        int totalPages,
        boolean last
        ) {

        @Builder
        public static record AppTechPlatformDto(
                AppTechPlatform[] appTechPlatform
        ) { }

    @Builder
    public static record OngoingGatheringResponseDto(
            String title,
            AppTechPlatform appTechPlatform,
            LocalDateTime startDate,
            LocalDateTime endDate,
            int workingDays,
            Long goalAmount,
            List<GatheringMember> gatheringMembers
    )
    { }
}
