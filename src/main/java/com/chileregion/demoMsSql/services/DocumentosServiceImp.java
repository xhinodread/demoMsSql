package com.chileregion.demoMsSql.services;

import com.chileregion.demoMsSql.domain.Documentos;
import com.chileregion.demoMsSql.persistance.entities.DocumentosEntity;
import com.chileregion.demoMsSql.persistance.repository.DocumentosRepository;
import com.chileregion.demoMsSql.utils.DocumentosMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("DBDOCUMENTOSDTE")
public class DocumentosServiceImp implements DocumentosService {
    @Autowired
    DocumentosRepository documentosRepository;
    @Autowired
    DocumentosMapper documentosMapper;
    @Override
    public Documentos getDocumentoId(Long id) {
        DocumentosEntity documento = documentosRepository.findByIdDteCabecera(id); /// .findDocumento(id);
        System.out.println("DOCUMENTO");
        System.out.println(documento + "\n");

        /**** /
        EmpresaEntity empresa = documento.getEmpresaEntity();
        ContribuyenteEntity contribuyente = documento.getContribuyenteEntity();

        System.out.println("documento");
        System.out.println(documento + "\n");
        System.out.println(empresa + "\n");
        System.out.println(contribuyente + "\n");
        / ****/
        // JMapper<Documentos, DocumentosEntity> documentoMapper = new JMapper<>(Documentos.class, DocumentosEntity.class);
        // Documentos destination = documentoMapper.getDestination(documento);
        return documentosMapper.mapper(documento);
    }

    @Override
    public Documentos getDocumentoFolio(Long folio, Long IdEmpresa) {

        DocumentosEntity documento = documentosRepository
                .findByFolioAndIdEmpresaAndIdOperacionAndTipo(folio.toString(), IdEmpresa, "1", "3");

        //System.out.println("DOCUMENTO FOLIO");
        //System.out.println(documento + "\n");

        return documentosMapper.mapper(documento);
    }


}
