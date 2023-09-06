package com.chileregion.demoMsSql.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentoReferencias {
    private Long idDteReferencia;
    private Long idDteCabecera;
    private String fechaReferencia;
    private String razonReferencia;
    private String tipoDocumentoReferencia;
    private String folioReferencia;
    private String codigoReferencia;
}
