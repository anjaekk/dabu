package b172.challenging.badge.repository;

import b172.challenging.badge.domain.BadgeMember;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

import static b172.challenging.auth.domain.QMember.member;
import static b172.challenging.badge.domain.QBadgeMember.badgeMember;
import static b172.challenging.badge.domain.QBadge.badge;


@Repository
public class BadgeCustomRepositoryImpl implements BadgeCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public BadgeCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<Tuple> findBadgeMemberWithBadgeByMemberId(Long memberId) {
        return jpaQueryFactory
                .select(badge,
                        Expressions.cases()
                                .when(badgeMember.id.isNull()).then(false)
                                .otherwise(true).as("has")
                )
                .from(badge)
                .leftJoin(badgeMember).on(badge.eq(badgeMember.badge).and(badgeMember.member.id.eq(memberId)))
                .orderBy(badge.id.asc())
                .fetch();
    }
}
