package b172.challenging.auth.oauth.filter;

import b172.challenging.auth.Repository.MemberRepository;
import b172.challenging.auth.domain.Member;
import b172.challenging.auth.service.JwtService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String ALLOW_PATH = "/login";

    private final JwtService jwtService;
    private final MemberRepository memberRepository;
    private GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain
    ) throws ServletException, IOException {
        if (request.getRequestURI().equals(ALLOW_PATH)) {
            filterChain.doFilter(request, response);
            return;
        }
        String refreshToken = jwtService.extractRefreshToken(request)
                .filter(jwtService::verifyToken)
                .orElse(null);
        /**
         * refresh token 전송시 access, refresh token 모두 재발급
         */
        if (refreshToken != null) {
            try {
                checkRefreshTokenAndReIssueTokens(response, refreshToken);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return;
        }

        if (refreshToken == null) {
            checkAccessTokenAndAuthentication(request, response, filterChain);
        }

    }

    public void checkRefreshTokenAndReIssueTokens(HttpServletResponse response, String refreshToken) throws Exception {
        Long memberId = jwtService.extractMemberId(refreshToken);

        Optional<Member> memberOptional = memberRepository.findById(memberId);
        if (memberOptional.isEmpty()) {
            throw new RuntimeException("사용자를 찾을 수 없습니다.");
        }

        Member member = memberOptional.get();
        String storedJwtCode = member.getJwtCode();

        String tokenJwtCode = jwtService.extractJwtCode(refreshToken);
        if (!tokenJwtCode.equals(storedJwtCode)) {
            throw new RuntimeException("Refresh Token이 올바르지 않습니다.");
        }

        jwtService.sendAccessAndRefreshToken(
                response, jwtService.createAccessToken(memberId), jwtService.createRefreshToken(memberId)
        );
    }

    public void checkAccessTokenAndAuthentication(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain
    ) throws ServletException, IOException {
        jwtService.extractAccessToken(request)
                .filter(jwtService::verifyToken)
                .ifPresent(accessToken -> {
                    try {
                        Long memberId = jwtService.extractMemberId(accessToken);
                        String jwtCode = jwtService.extractJwtCode(accessToken);
                        verifyJwtCodeAndAuthenticate(memberId, jwtCode);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
        filterChain.doFilter(request, response);
    }

    public void verifyJwtCodeAndAuthenticate(Long memberId, String jwtCode) {
        memberRepository.findJwtCodeById(memberId)
                .filter(savedJwtCode -> savedJwtCode.equals(jwtCode))
                .ifPresentOrElse(
                        savedJwtCode -> {
                            Member member = memberRepository.findById(memberId)
                                    .orElseThrow(() -> new EntityNotFoundException(
                                            "사용자 ID: " + memberId + "를 찾을 수 없습니다."
                                            )
                                    );

                            Authentication authentication =
                                    new UsernamePasswordAuthenticationToken(member, null);

                            SecurityContextHolder.getContext().setAuthentication(authentication);
                        },
                        () -> {
                            throw new BadCredentialsException("JWT 코드가 일치하지 않습니다.");
                        }
                );
    }
}
