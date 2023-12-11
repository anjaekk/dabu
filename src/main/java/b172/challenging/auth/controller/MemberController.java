package b172.challenging.auth.controller;

import b172.challenging.auth.domain.Member;
import b172.challenging.auth.dto.response.MemberProfileResponseDto;
import b172.challenging.auth.service.MemberService;
import b172.challenging.common.exception.CustomRuntimeException;
import b172.challenging.common.exception.ErrorCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "Member", description = "Member 관련 API")
@RequiredArgsConstructor
@RequestMapping("/v1/members")
@RestController
public class MemberController {

    public final MemberService memberService;

    @GetMapping("/profile")
    @Operation(summary = "사용자 정보", description = "토큰 정보에 해당하는 사용자 정보를 반환합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = {@Content(schema = @Schema(implementation = MemberProfileResponseDto.class))}),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자 입니다."),
    })
    public ResponseEntity<MemberProfileResponseDto> getMemberProfile(@AuthenticationPrincipal Member member) {
        if (member == null) {
            throw new CustomRuntimeException(ErrorCode.UNAUTHORIZED);
        }
        return ResponseEntity.ok(MemberProfileResponseDto.of(member));
    }
}
