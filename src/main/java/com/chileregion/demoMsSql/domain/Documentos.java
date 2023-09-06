package com.chileregion.demoMsSql.domain;

import com.googlecode.jmapper.annotations.JMap;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Documentos {

    @JMap
    private Long IdDteCabecera;
    @JMap
    private String tipo;
    /// private String rut;
    @JMap
    private long IdEmpresa;
    @JMap
    private String folio;
    @JMap
    private String fecha_emision;
    @JMap
    private String receptor;
    @JMap
    private String razon_receptor;
    @JMap
    private Long monto_total;
    @JMap
    private Long monto_real;
    @JMap
    private String estado;

    private Empresa empresa;
    private Contribuyente contribuyente;
    private List<DocumentoDetalles> documentoDetalles;
    private List<DocumentoReferencias> documentoReferencias;

}
