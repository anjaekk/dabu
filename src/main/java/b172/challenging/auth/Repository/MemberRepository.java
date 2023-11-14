package b172.challenging.auth.Repository;

import b172.challenging.auth.domain.Member;
import b172.challenging.auth.domain.OauthProvider;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public Member save(Member member) {
        if(member.getId() == null) {
            em.persist(member);
        } else {
            em.merge(member);
        }
        return member;
    }

    public Optional<Member> findOneByOauthProviderAndOauthId(OauthProvider oauthProvider, String oauthId) {
        List<Member> resultList = em.createQuery(
                "select m from Member m where m.oauthId = :oauthId and m.oauthProvider = :oauthProvider",
                        Member.class
                )
                .setParameter("oauthId", oauthId)
                .setParameter("oauthProvider", oauthProvider)
                .getResultList();
        return resultList.isEmpty() ? Optional.empty() : Optional.of(resultList.get(0));
    }

    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    public Optional<String> findJwtCodeById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member != null ? member.getJwtCode() : null);
    }

    @Transactional
    public void updateJwtCode(Long memberId, String jwtCode) {
        Member member = em.find(Member.class, memberId);
        member.setJwtCode(jwtCode);
    }
}
