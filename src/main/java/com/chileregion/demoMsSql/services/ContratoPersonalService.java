package com.chileregion.demoMsSql.services;

import com.chileregion.demoMsSql.domain.ContratoPersonal;

import java.util.List;

public interface ContratoPersonalService {

    public List<ContratoPersonal> getContratosEmpresas(Long idEmpresa);

}
