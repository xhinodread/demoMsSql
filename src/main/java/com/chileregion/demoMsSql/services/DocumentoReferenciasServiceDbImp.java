package com.chileregion.demoMsSql.services;

import com.chileregion.demoMsSql.domain.DocumentoReferencias;
import com.chileregion.demoMsSql.persistance.entities.DocumentoReferenciasEntity;
import com.chileregion.demoMsSql.persistance.repository.DocumentoReferenciasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("DBDOCUMENTOSDTEREFETENCIAS")
public class DocumentoReferenciasServiceDbImp implements DocumentoReferenciasService {

    @Autowired
    DocumentoReferenciasRepository documentoReferenciasRepository;

    @Override
    public String setDocumentoReferencias(DocumentoReferencias documentoReferencias) {
        //System.out.println("setDocumentoReferencias \n" + documentoReferencias + "\n");
        DocumentoReferenciasEntity myDocumentoReferenciasEntity = new DocumentoReferenciasEntity(
                documentoReferencias.getIdDteReferencia(),
                documentoReferencias.getIdDteCabecera(),
                documentoReferencias.getFechaReferencia(),
                documentoReferencias.getRazonReferencia(),
                documentoReferencias.getTipoDocumentoReferencia(),
                documentoReferencias.getFolioReferencia(),
                documentoReferencias.getCodigoReferencia()
        );
        documentoReferenciasRepository.save(myDocumentoReferenciasEntity);
        return null;
    }
}
