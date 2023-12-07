package b172.challenging.gathering.controller;


import b172.challenging.gathering.domain.AppTechPlatform;
import b172.challenging.gathering.domain.Gathering;
import b172.challenging.gathering.domain.GatheringStatus;
import b172.challenging.gathering.dto.GatheringMemberPageResponseDto;
import b172.challenging.gathering.dto.GatheringPageResponseDto;
import b172.challenging.gathering.service.GatheringService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Tag(name = "함께 모으기 API", description = "함께 모으기 (홈 화면) API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/gathering")
public class GatheringController {

    private final GatheringService gatheringService;

    @GetMapping("/in-progress/{userId}")
    @Parameter(name = "userId", description = "User Id", example = "user")
    public ResponseEntity<GatheringMemberPageResponseDto> getInProgressMyGathering (@PathVariable Long userId,
                                                                                    @PageableDefault(size = 5, direction = Sort.Direction.DESC) Pageable page){
        return ResponseEntity.ok(gatheringService.findMyGatheringInProgress(userId ,page));
    }

    @GetMapping(value = {"/{status}/{platform}", "/{status}"})
    @Parameter(name = "status", description = "status : [PENDING] or [ONGOING, COMPLETED]", example = "TOSS")
    @Parameter(name = "platform", description = "platform : [TOSS, CASS_WORK, MONIMO, BALSO]", example = "TOSS")
    public ResponseEntity<GatheringPageResponseDto> getGathering (@PathVariable(required = false) AppTechPlatform platform,
                                                                  @PathVariable GatheringStatus status ,
                                                                  @PageableDefault(size = 5, direction = Sort.Direction.DESC) Pageable page){
        return platform == null
                ? ResponseEntity.ok(gatheringService.findAllGathering(status ,page))
                : ResponseEntity.ok(gatheringService.findGatheringByPlatform(platform, status, page));
    }
}
