package com.chileregion.demoMsSql.persistance.repository;

import com.chileregion.demoMsSql.persistance.entities.EmpresaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpresaRepository extends JpaRepository<EmpresaEntity, Long> {

    // List<EmpresaEntity> findByRazonSocial(String RazonSocial);

     List<EmpresaEntity> findAll();


}
