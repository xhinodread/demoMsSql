package com.chileregion.demoMsSql.services;

import com.chileregion.demoMsSql.domain.Feriados;
import com.chileregion.demoMsSql.persistance.entities.FeriadosEntity;

import java.util.List;

public interface FeriadosService {

    List<Feriados> getFeriados(String desde, String hasta);

}
