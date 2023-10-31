package com.chileregion.demoMsSql.persistance.repository;

import com.chileregion.demoMsSql.persistance.entities.DocumentosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface DocumentosRepository extends JpaRepository<DocumentosEntity, Long> {


    /*****
    //@Query("SELECT d FROM DteCabecera d WHERE d.IdDteCabecera = ?1")
    @Query("SELECT d, e FROM DteCabecera d join Empresa e on d.IdEmpresa = e.id WHERE d.IdDteCabecera = ?1")
    DocumentosEntity findDocumento(Long id);
    ******/

    DocumentosEntity findByIdDteCabecera(Long idDteCabecera);

    DocumentosEntity findByFolioAndIdEmpresa(String folio, Long idEmpresa);
    //getDocumentoFolioAndIdEmpresa

    DocumentosEntity findByFolioAndIdEmpresaAndIdOperacionAndTipo(String folio, Long idEmpresa, String idOperacion, String tipo);
    long countByFolioAndIdEmpresaAndIdOperacionAndTipo(String folio, Long idEmpresa, String idOperacion, String tipo);

    @Transactional
    @Modifying
    @Query(
        value = "Update DteCabecera Set Folio = ?1 where idDteCabecera = ?2",
        nativeQuery = true
    )
    int cambiarFolio(Long nuevo_folio, Long id_dtecabecera );




}
