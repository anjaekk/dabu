package b172.challenging.auth.oauth;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import javax.management.relation.Role;
import java.util.Collection;
import java.util.Map;

@Getter
public class CustomOauth2User extends DefaultOAuth2User {
    private Long memberId;

    public CustomOauth2User(Collection<? extends GrantedAuthority> authorities,
                            Map<String, Object> attributes, String nameAttributeKey,
                            Long id) {
        super(authorities, attributes, nameAttributeKey);
        this.memberId = id;
    }
}
