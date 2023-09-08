package com.chileregion.demoMsSql.domain;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContratoVacaciones {
    private Long idVacacion;
    private Long idContratoPersonal;
    private String periodo;
    private String desde;
    private String hasta;
    private Integer dias;
}
