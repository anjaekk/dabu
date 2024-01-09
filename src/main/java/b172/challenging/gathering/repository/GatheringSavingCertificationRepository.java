package b172.challenging.gathering.repository;

import b172.challenging.gathering.domain.GatheringSavingCertification;
import b172.challenging.gathering.domain.GatheringSavingLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GatheringSavingCertificationRepository extends JpaRepository<GatheringSavingCertification, Long> {
    Optional<GatheringSavingCertification> findByGatheringSavingLog(GatheringSavingLog gatheringSavingLog);
}
