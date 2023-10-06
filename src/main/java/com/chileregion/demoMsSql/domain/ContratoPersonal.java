package com.chileregion.demoMsSql.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContratoPersonal {
// IdContratoPersonal	, FechaInicio, FechaTermino, FechaIngreso, CodUsuarioIngreso, IdContratoTipoEmpresa, IdContribuyente, IdEmpresa, DiasVacaciones
    private Long idContratoPersonal;
    private Long idContribuyente;
    private Long idEmpresa;
    private String fechaInicio;
    private String fechaTermino;
    private String fechaIngreso;
    private Integer diasVacaciones;
    private Contribuyente contribuyente;

}
