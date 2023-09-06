package com.chileregion.demoMsSql.services;

import com.chileregion.demoMsSql.domain.Documentos;
import com.chileregion.demoMsSql.persistance.entities.DocumentosEntity;

public interface DocumentosService {

    Documentos getDocumentoId(Long id);


}
