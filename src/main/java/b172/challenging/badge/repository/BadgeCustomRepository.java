package b172.challenging.badge.repository;

import b172.challenging.badge.domain.BadgeMember;
import com.querydsl.core.Tuple;

import java.util.List;
import java.util.Optional;

public interface BadgeCustomRepository {
    List<Tuple> findBadgeMemberWithBadgeByMemberId(Long memberId);
}
