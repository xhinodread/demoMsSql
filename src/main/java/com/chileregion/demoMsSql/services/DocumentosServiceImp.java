package com.chileregion.demoMsSql.services;

import com.chileregion.demoMsSql.domain.Documentos;
import com.chileregion.demoMsSql.persistance.entities.DocumentosEntity;
import com.chileregion.demoMsSql.persistance.repository.DocumentosRepository;
import com.chileregion.demoMsSql.utils.DocumentosMapper;
import jakarta.persistence.EntityNotFoundException;
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

    @Override
    public Long getDocumentoFolioNuevo(Long folio, Long IdEmpresa) {
        DocumentosEntity documento;

        Long countadocFolio = documentosRepository
                .countByFolioAndIdEmpresaAndIdOperacionAndTipo(folio.toString(), IdEmpresa, "1", "3");
        //System.out.println("DOCUMENTO nuevo FOLIO");
        //System.out.println(countadocFolio + "\n");
        return countadocFolio;
        //return documentosMapper.mapper(documento);
    }

    @Override
    public String setNuevoFolio(Long nuevo_folio, Long id_dtecabecera) {
        System.out.println("setNuevoFolio");
        System.out.println(nuevo_folio + "\n" + id_dtecabecera);

        int cambio = documentosRepository.cambiarFolio(nuevo_folio,id_dtecabecera);
        System.out.println("setNuevoFolio out....");

        return String.valueOf(cambio);
    }


}
