package com.chileregion.demoMsSql.services;

import com.chileregion.demoMsSql.domain.Empresa;
import com.chileregion.demoMsSql.persistance.entities.EmpresaEntity;
import com.chileregion.demoMsSql.persistance.repository.EmpresaRepository;
import com.googlecode.jmapper.JMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service("BD")
public class EmpresaServiceDbImp implements EmpresaService {
    @Autowired
    EmpresaRepository empresaRepository;

    @Override
    public List<Empresa> getEmpresas() {

        //JMapper<Empresa, EmpresaEntity> mapper = new JMapper<>(Empresa.class, EmpresaEntity.class);
        ////JMapper<Empresa, EmpresaEntity> jmapper = new JMapper<>(Empresa.class, EmpresaEntity.class);

       // List<EmpresaEntity> empresasX = empresaRepository.findAll();
       // System.out.println("empresasX: " + empresasX);


        List<Empresa> empresas = empresaRepository.findAll()
                .stream()
                .map( empresaEntity ->{
                  //  System.out.println("empresaEntity: " + empresaEntity);

                  /*****  Empresa empresaX = (Empresa) jmapper.getDestination(empresaEntity);   *****/
                  //  System.out.println("empresaX: " + empresaX);

                    Empresa empresa = new Empresa(
                            empresaEntity.getIdEmpresa(),
                            empresaEntity.getRUT(),
                            empresaEntity.getRazonSocial()
                    );

                    return empresa;
                }).collect(Collectors.toList());

        return empresas;
    }

    @Override
    public void saveEmpresa(Empresa empresa) {

    }
}
