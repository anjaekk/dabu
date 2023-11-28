package b172.challenging.auth.Repository;

import b172.challenging.auth.domain.Member;
import b172.challenging.auth.domain.OauthProvider;

import java.util.Optional;

public interface MemberCustomRepository {

    Optional<String> findJwtCodeById(Long id);

    Long updateJwtCodeById(Long memberId, String jwtCode);
}
