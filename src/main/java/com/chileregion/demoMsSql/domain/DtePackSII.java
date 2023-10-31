package com.chileregion.demoMsSql.domain;

import com.googlecode.jmapper.annotations.JMap;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtePackSII {
    @JMap
    private Long idDtePackSII;
    private String fechaIngreso;
    private String trackId;
    private Long idDteCabecera;
    private String estado;
    private String aceptado;
}
