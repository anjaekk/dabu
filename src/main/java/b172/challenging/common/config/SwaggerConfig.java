package b172.challenging.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Value("${spring.security.oauth2.client.provider.kakao.authorization-uri}")
    private String authorizationUrl;

    @Value("${spring.security.oauth2.client.provider.kakao.token-uri}")
    private String tokenUrl;

    @Bean
    public OpenAPI openAPI() {

        // SecuritySecheme명
        String oauth2Scheme = "oauth2";
        // API 요청헤더에 인증정보 포함
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(oauth2Scheme);

        // SecuritySchemes 등록
        Components components = new Components()
                .addSecuritySchemes(oauth2Scheme, new SecurityScheme()
                        .name(oauth2Scheme)
                        .type(SecurityScheme.Type.OAUTH2) // OAUTH2 방식
                        .flows(
                                new OAuthFlows()
                                        .authorizationCode(new OAuthFlow()
                                                .tokenUrl(tokenUrl)
                                                .authorizationUrl(authorizationUrl))
                        ));

        return new OpenAPI()
                .components(components)
                .addSecurityItem(securityRequirement)
                .info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("Springdoc 테스트")
                .description("Springdoc을 사용한 Swagger UI 테스트")
                .version("1.0.0");
    }
}