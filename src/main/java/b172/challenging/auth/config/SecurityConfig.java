package b172.challenging.auth.config;

import b172.challenging.auth.oauth.CustomAuthenticationEntryPoint;
import b172.challenging.auth.repository.MemberRepository;
import b172.challenging.auth.oauth.filter.JwtAuthenticationFilter;
import b172.challenging.auth.oauth.handler.Oauth2LoginFailureHandler;
import b172.challenging.auth.oauth.handler.Oauth2LoginSuccessHandler;
import b172.challenging.auth.service.CustomOauthService;
import b172.challenging.auth.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Slf4j
@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtService jwtService;
    private final MemberRepository memberRepository;
    private final CustomOauthService customOauthService;
    private final Oauth2LoginSuccessHandler oauth2LoginSuccessHandler;
    private final Oauth2LoginFailureHandler oauth2LoginFailureHandler;


    @Bean
    public WebSecurityCustomizer configureH2ConsoleEnable() {
        return (web) -> web.ignoring()
                .requestMatchers(PathRequest.toH2Console());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement((sessionManagement) ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests((authorizeRequests) -> authorizeRequests
                        .requestMatchers(new AntPathRequestMatcher("/")
                                , new AntPathRequestMatcher("/css/**")
                                , new AntPathRequestMatcher("/images/**")
                                , new AntPathRequestMatcher("/js/**")
                                , new AntPathRequestMatcher("/favicon.ico")
                                , new AntPathRequestMatcher("/login/**")
                                , new AntPathRequestMatcher("/oauth2/**")
                                , new AntPathRequestMatcher("/h2-console/**")
                                , new AntPathRequestMatcher("/swagger-ui/**")
                                , new AntPathRequestMatcher("/api-docs/**")
                                , new AntPathRequestMatcher("/example/**")
                        ).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/v1/members/**")).hasRole("ACTIVE")
                        .anyRequest().authenticated()
                )
                .oauth2Login((oauth2) -> oauth2
                        .successHandler(oauth2LoginSuccessHandler)
                        .failureHandler(oauth2LoginFailureHandler)
                        .userInfoEndpoint((userInfoEndpoint -> userInfoEndpoint
                                .userService(customOauthService)))
                )
                .exceptionHandling((exceptionHandling) ->
                        exceptionHandling.authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                );
        http.addFilterAfter(jwtAuthenticationFilter(), LogoutFilter.class);
        return http.build();
    }

    @Bean
    public Oauth2LoginSuccessHandler loginSuccessHandler() {
        return new Oauth2LoginSuccessHandler(jwtService, customOauthService);
    }

    @Bean
    public Oauth2LoginFailureHandler loginFailureHandler() {
        return new Oauth2LoginFailureHandler();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(jwtService, memberRepository);
    }

}

