package com.chileregion.demoMsSql.persistance.repository;

import com.chileregion.demoMsSql.domain.ContratoVacaciones;
import com.chileregion.demoMsSql.persistance.entities.ContratoVacacionesEntity;
import com.chileregion.demoMsSql.persistance.entities.DocumentoDetallesEntity;
import com.chileregion.demoMsSql.persistance.entities.DocumentosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContratoVacacionesRepository extends JpaRepository<ContratoVacacionesEntity, Long> {
    ContratoVacacionesEntity findByIdContratoPersonal(Long idContratoPersonal);

}
