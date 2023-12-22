package b172.challenging.gathering.repository;

import b172.challenging.gathering.domain.GatheringMember;
import b172.challenging.gathering.domain.GatheringMemberStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GatheringMemberRepository extends JpaRepository<GatheringMember, Long> {

    @EntityGraph(attributePaths = {"gathering","member"})
    Page<GatheringMember> findByMemberIdAndStatus(Long id, GatheringMemberStatus status, Pageable pageable);
}
