package b172.challenging.protip.dto;

import b172.challenging.member.domain.Role;
import b172.challenging.protip.domain.ProTip;
import lombok.Builder;

import java.util.List;

@Builder
public record ProTipResponseDto(
        List<ProTip> proTips,
        int pageNo,
        int pageSize,
        long totalElements,
        int totalPages,
        boolean last,
        Role role
) { }
