package b172.challenging.auth.oauth.filter;

import b172.challenging.auth.Repository.MemberRepository;
import b172.challenging.auth.domain.Member;
import b172.challenging.auth.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
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
}
