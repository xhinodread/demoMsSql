package com.chileregion.demoMsSql.persistance.repository;

import com.chileregion.demoMsSql.domain.ContratoVacaciones;
import com.chileregion.demoMsSql.persistance.entities.ContratoVacacionesEntity;
import com.chileregion.demoMsSql.persistance.entities.DocumentoDetallesEntity;
import com.chileregion.demoMsSql.persistance.entities.DocumentosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContratoVacacionesRepository extends JpaRepository<ContratoVacacionesEntity, Long> {
    List<ContratoVacacionesEntity> findByIdContratoPersonal(Long idContratoPersonal);

}
