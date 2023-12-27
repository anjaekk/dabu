package b172.challenging.auth.oauth.handler;

import b172.challenging.auth.domain.Role;
import b172.challenging.auth.oauth.CustomOauth2User;
import b172.challenging.auth.service.CustomOauthService;
import b172.challenging.auth.service.JwtService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class Oauth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtService jwtService;
    private final CustomOauthService customOauthService;


    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request, HttpServletResponse response, Authentication authentication
    ) throws IOException, ServletException {
        CustomOauth2User oauth2User = (CustomOauth2User) authentication.getPrincipal();
        Long memberId = oauth2User.getMemberId();
        String accessToken = jwtService.createAccessToken(memberId);
        String refreshToken = jwtService.createRefreshToken(memberId);
        if (oauth2User.getRole() == Role.GUEST) {
            response.addHeader(jwtService.getAccessHeader(), "Bearer " + accessToken);
            response.sendRedirect("/signup-form"); // FIXME: 추가 정보 입력창으로 redirect
        } else {
            jwtService.sendAccessAndRefreshToken(response, accessToken, refreshToken);
        }
    }
}
