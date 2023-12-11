package b172.challenging.auth.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
    ROLE_GUEST("GUEST"),
    ROLE_MEMBER("MEMBER"),
    ROLE_ADMIN("ADMIN");

    private final String key;
}
