package com.chileregion.demoMsSql.services;

import com.chileregion.demoMsSql.domain.Documentos;
import com.chileregion.demoMsSql.persistance.entities.DocumentosEntity;

public interface DocumentosService {

    Documentos getDocumentoId(Long id);

    Documentos getDocumentoFolio(Long folio, Long IdEmpresa);

    Long getDocumentoFolioNuevo(Long folio, Long IdEmpresa);

    String setNuevoFolio(Long nuevo_folio, Long id_dtecabecera);

}
