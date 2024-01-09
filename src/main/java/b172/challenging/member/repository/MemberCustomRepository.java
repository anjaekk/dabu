package b172.challenging.member.repository;

import java.util.Optional;

public interface MemberCustomRepository {

    Optional<String> findJwtCodeById(Long id);

    Long updateJwtCodeById(Long memberId, String jwtCode);

}
