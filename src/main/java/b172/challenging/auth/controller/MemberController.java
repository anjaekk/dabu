package b172.challenging.auth.controller;

import b172.challenging.auth.domain.Member;
import b172.challenging.auth.dto.response.MemberProfileResponseDto;
import b172.challenging.auth.service.MemberService;
import b172.challenging.common.exception.CustomRuntimeException;
import b172.challenging.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RequestMapping("/v1/members")
@RestController
public class MemberController {

    public final MemberService memberService;

    @GetMapping("/profile")
    public ResponseEntity<MemberProfileResponseDto> getMemberProfile(@AuthenticationPrincipal Member member) {
        if (member == null) {
            throw new CustomRuntimeException(ErrorCode.UNAUTHORIZED);
        }
        return ResponseEntity.ok(MemberProfileResponseDto.of(member));
    }
}
