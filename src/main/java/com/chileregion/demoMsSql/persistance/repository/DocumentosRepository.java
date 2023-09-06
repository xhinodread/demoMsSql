package com.chileregion.demoMsSql.persistance.repository;

import com.chileregion.demoMsSql.domain.Documentos;
import com.chileregion.demoMsSql.persistance.entities.DocumentosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface DocumentosRepository extends JpaRepository<DocumentosEntity, Long> {


    /*****
    //@Query("SELECT d FROM DteCabecera d WHERE d.IdDteCabecera = ?1")
    @Query("SELECT d, e FROM DteCabecera d join Empresa e on d.IdEmpresa = e.id WHERE d.IdDteCabecera = ?1")
    DocumentosEntity findDocumento(Long id);
    ******/

    DocumentosEntity findByIdDteCabecera(Long idDteCabecera);


}
