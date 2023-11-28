package b172.challenging.auth.Repository;

import b172.challenging.auth.domain.Member;
import b172.challenging.auth.domain.OauthProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberCustomRepository {

    Optional<Member> findByOauthProviderAndOauthId(OauthProvider oauthProvider, String oauthId);

    Optional<Member> findByNickname(String nickname);

}