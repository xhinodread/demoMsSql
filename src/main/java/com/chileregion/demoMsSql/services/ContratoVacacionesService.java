package com.chileregion.demoMsSql.services;

import com.chileregion.demoMsSql.domain.ContratoVacaciones;

public interface ContratoVacacionesService {
    ContratoVacaciones getContratoVacaciones(Long idContratoPersonal);

    String setContratoVacaciones(ContratoVacaciones contratoVacaciones);
}
