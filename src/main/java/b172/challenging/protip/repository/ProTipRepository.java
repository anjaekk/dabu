package b172.challenging.protip.repository;

import b172.challenging.common.domain.UseYn;
import b172.challenging.protip.domain.ProTip;
import b172.challenging.protip.domain.ProTipType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProTipRepository extends JpaRepository<ProTip,Long> {
    Page<ProTip> findByUseYnIs(UseYn useYn, Pageable page);

    Page<ProTip> findByTypeAndUseYnIs(ProTipType type, UseYn useYn, Pageable page);

    Page<ProTip> findByType(ProTipType type, Pageable page);
}
