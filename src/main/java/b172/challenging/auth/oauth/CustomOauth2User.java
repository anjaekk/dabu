package b172.challenging.auth.oauth;

import b172.challenging.auth.domain.Role;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import java.util.Collection;
import java.util.Map;

@Getter
public class CustomOauth2User extends DefaultOAuth2User {
    private Long memberId;
    private Role role;

    public CustomOauth2User(Collection<? extends GrantedAuthority> authorities,
                            Map<String, Object> attributes, String nameAttributeKey,
                            Long id, Role role) {
        super(authorities, attributes, nameAttributeKey);
        this.memberId = id;
        this.role = role;
    }
}
