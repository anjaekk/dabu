package b172.challenging.gathering.service;

import b172.challenging.gathering.domain.*;
import b172.challenging.gathering.dto.GatheringMemberPageResponseDto;
import b172.challenging.gathering.dto.GatheringPageResponseDto;
import b172.challenging.gathering.repository.GatheringMemberRepository;
import b172.challenging.gathering.repository.GatheringRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GatheringService {
    private final GatheringRepository gatheringRepository;
    private final GatheringMemberRepository gatheringMemberRepository;

    public GatheringPageResponseDto findGatheringByPlatform (AppTechPlatform platform, GatheringStatus status, Pageable page){
        Page<Gathering> gatheringsPage =
                status.equals(GatheringStatus.PENDING)
                ?  gatheringRepository.findByPlatformAndStatus(platform, GatheringStatus.PENDING, page)
                :  gatheringRepository.findByPlatformAndStatusNot(platform, GatheringStatus.PENDING, page);


        List<Gathering> gatherings  = new ArrayList<>(gatheringsPage.getContent());


        return GatheringPageResponseDto.builder()
                .gatherings(gatherings)
                .pageNo(page.getPageNumber())
                .pageSize(page.getPageSize())
                .totalElements(gatheringsPage.getTotalElements())
                .totalPages(gatheringsPage.getTotalPages())
                .last(gatheringsPage.isLast())
                .build();
    }

    public GatheringPageResponseDto findAllGathering(GatheringStatus status, Pageable page) {
        Page<Gathering> gatheringsPage =
                status.equals(GatheringStatus.PENDING)
                ? gatheringRepository.findByStatus(GatheringStatus.PENDING, page)
                : gatheringRepository.findByStatusNot(GatheringStatus.PENDING, page);
        List<Gathering> gatherings  = new ArrayList<>(gatheringsPage.getContent());


        return GatheringPageResponseDto.builder()
                .gatherings(gatherings)
                .pageNo(page.getPageNumber())
                .pageSize(page.getPageSize())
                .totalElements(gatheringsPage.getTotalElements())
                .totalPages(gatheringsPage.getTotalPages())
                .last(gatheringsPage.isLast())
                .build();
    }

    public GatheringMemberPageResponseDto findMyGatheringInProgress(Long userId, Pageable page) {
        Page<GatheringMember> gatheringMembersPage = gatheringMemberRepository.findByMemberIdAndStatus(userId, GatheringMemberStatus.ONGOING, page);
        List<GatheringMember> gatheringMembers  = new ArrayList<>(gatheringMembersPage.getContent());
        return GatheringMemberPageResponseDto.builder()
                .gatheringMembers(gatheringMembers)
                .pageNo(page.getPageNumber())
                .pageSize(page.getPageSize())
                .totalElements(gatheringMembersPage.getTotalElements())
                .totalPages(gatheringMembersPage.getTotalPages())
                .last(gatheringMembersPage.isLast())
                .build();
    }
}
