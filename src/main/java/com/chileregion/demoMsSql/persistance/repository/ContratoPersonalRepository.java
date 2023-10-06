package com.chileregion.demoMsSql.persistance.repository;

import com.chileregion.demoMsSql.persistance.entities.ContratoPersonalEntity;
import com.chileregion.demoMsSql.persistance.entities.DocumentosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContratoPersonalRepository extends JpaRepository<ContratoPersonalEntity, Long> {

    List<ContratoPersonalEntity> findByIdEmpresa(Long idEmpresa);

}
