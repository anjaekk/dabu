package b172.challenging.gathering.dto.response;

import b172.challenging.member.domain.Member;
import lombok.Builder;

@Builder
public record GatheringMakeResponseDto (
        Long id,
        String title,
        Member owner
){ }
