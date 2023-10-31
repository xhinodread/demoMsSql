package com.chileregion.demoMsSql.persistance.repository;

import com.chileregion.demoMsSql.persistance.entities.DtePackSIIEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DtePackSIIRepository extends JpaRepository<DtePackSIIEntity, Long> {

    DtePackSIIEntity findByIdDteCabecera(Long idDteCabecera);
    @Transactional
    Long deleteByIdDteCabecera(Long idDteCabecera);

}
