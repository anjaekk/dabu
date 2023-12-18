package b172.challenging.gathering.controller;


import b172.challenging.gathering.domain.AppTechPlatform;
import b172.challenging.gathering.domain.GatheringStatus;
import b172.challenging.gathering.dto.*;
import b172.challenging.gathering.service.GatheringService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

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
    @Parameter(name = "platform", description = "platform : [TOSS, CASH_WORK, MONIMO, BALSO]", example = "TOSS")
    public ResponseEntity<GatheringPageResponseDto> getGathering (@PathVariable(required = false) AppTechPlatform platform,
                                                                  @PathVariable GatheringStatus status ,
                                                                  @PageableDefault(size = 5, direction = Sort.Direction.DESC) Pageable page){
        return platform == null
                ? ResponseEntity.ok(gatheringService.findAllGathering(status ,page))
                : ResponseEntity.ok(gatheringService.findGatheringByPlatform(platform, status, page));
    }

    @GetMapping(value = "/platform")
    public ResponseEntity<AppTechPlatformDto> getPlatform (){
        return ResponseEntity.ok(
                AppTechPlatformDto.builder()
                        .appTechPlatform(AppTechPlatform.values())
                        .build()
        );
    }

    @PostMapping("")
    @Operation(summary = "모임 만들기", description = "새로운 모임을 만듭니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "성공"),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자 입니다."),
    })
    public ResponseEntity<GatheringMakeResponseDto> makeGathering(Principal principal,
                                                                  @RequestBody @Valid GatheringMakeRequestDto gatheringMakeRequestDto){
        Long memberId = Long.parseLong(principal.getName());
        return ResponseEntity.ok(gatheringService.makeGathering(memberId,gatheringMakeRequestDto));
    }

    @GetMapping("/info/{status}/{gatheringId}/{userId}")
    @Operation(summary = "모임 상세 정보", description = "모임 상세 정보를 가져옵니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자 입니다."),
    })
    public ResponseEntity<GatheringStatusResponseDto> getGatheringStatus( @PathVariable GatheringStatus status,
                                                      @PathVariable Long gatheringId,
                                                      @PathVariable Long userId
    ){
        return ResponseEntity.ok(gatheringService.findGatheringStatus(gatheringId, userId, status));
    }

    @PutMapping("/join/{gatheringId}/{userId}")
    @Operation(summary = "모임 참가 하기", description = "현재 유저가 모임에 참가합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "성공"),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자 입니다."),
    })
    public ResponseEntity<String> joinGathering(){

        return ResponseEntity.ok("");
    }

    @PutMapping("/left/{gatheringId}/{userId}")
    @Operation(summary = "모임 나가기", description = "현재 유저가 모임에서 나갑니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "성공"),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자 입니다."),
    })
    public ResponseEntity<String> leftGathering(){

        return ResponseEntity.ok("");
    }
}
