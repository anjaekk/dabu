package b172.challenging.auth.Repository;

import b172.challenging.auth.domain.Member;
import b172.challenging.auth.domain.OauthProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByOauthProviderAndOauthId(OauthProvider oauthProvider, String oauthId);

    Optional<Member> findByNickname(String nickname);

    @Query("SELECT m.jwtCode FROM Member m WHERE m.id = :id")
    Optional<String> findJwtCodeById(@Param("id") Long id);
}