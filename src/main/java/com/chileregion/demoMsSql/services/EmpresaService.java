package com.chileregion.demoMsSql.services;

import com.chileregion.demoMsSql.domain.Empresa;

import java.util.List;

public interface EmpresaService {
    public List<Empresa> getEmpresas();

    public void saveEmpresa(Empresa empresa);

}
