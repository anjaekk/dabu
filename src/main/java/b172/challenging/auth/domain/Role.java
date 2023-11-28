package b172.challenging.auth.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
    PENDING("PENDING"),
    ACTIVE("ACTIVE"),
    STAFF("STAFF");

    private final String key;
}
