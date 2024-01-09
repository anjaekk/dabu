package b172.challenging.member.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static b172.challenging.member.domain.QMember.member;

@Repository
public class MemberCustomRepositoryImpl implements MemberCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public MemberCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }


    @Override
    public Optional<String> findJwtCodeById(Long id) {
        String jwtCode = jpaQueryFactory
                .select(member.jwtCode)
                .from(member)
                .where(member.id.eq(id))
                .fetchOne();
        return Optional.ofNullable(jwtCode);
    }

    @Override
    @Transactional
    public Long updateJwtCodeById(Long memberId, String jwtCode) {
        return jpaQueryFactory
                .update(member)
                .set(member.jwtCode, jwtCode)
                .where(member.id.eq(memberId))
                .execute();
    }
}
