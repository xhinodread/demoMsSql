package com.chileregion.demoMsSql.services;

import com.chileregion.demoMsSql.domain.ContratoVacaciones;

import java.util.List;

public interface ContratoVacacionesService {
    List<ContratoVacaciones> getContratoVacaciones(Long idContratoPersonal);

    String setContratoVacaciones(ContratoVacaciones contratoVacaciones);
}
