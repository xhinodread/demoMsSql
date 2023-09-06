package com.chileregion.demoMsSql.persistance.repository;

import com.chileregion.demoMsSql.persistance.entities.DocumentoReferenciasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentoReferenciasRepository extends JpaRepository<DocumentoReferenciasEntity, Long> {



}
