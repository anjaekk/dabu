package b172.challenging.auth.controller;

import b172.challenging.member.dto.response.MemberProfileResponseDto;
import b172.challenging.member.repository.MemberRepository;
import b172.challenging.auth.service.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Tag(name = "Auth", description = "Auth 관련 API")
@RequiredArgsConstructor
@RequestMapping("/oauth")
@RestController
public class AuthController {

    private final JwtService jwtService;
    public final MemberRepository memberRepository;


    @PostMapping("/logout")
    @Operation(summary = "로그아웃", description = "로그인된 사용자를 로그아웃 시킵니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = {@Content(schema = @Schema(implementation = MemberProfileResponseDto.class))}),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자 입니다."),
    })
    public ResponseEntity<Void> logout(Principal principal) {
        Long memberId = Long.parseLong(principal.getName());
        jwtService.checkMemberId(memberId);
        jwtService.saveRandomJwtCode(memberId);
        return ResponseEntity.ok().build();
    }
}
