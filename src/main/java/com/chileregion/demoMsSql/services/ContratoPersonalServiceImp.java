package com.chileregion.demoMsSql.services;

import com.chileregion.demoMsSql.domain.ContratoPersonal;
import com.chileregion.demoMsSql.domain.Contribuyente;
import com.chileregion.demoMsSql.persistance.entities.ContratoPersonalEntity;
import com.chileregion.demoMsSql.persistance.repository.ContratoPersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("DBCONTRATOPERSONAL")
public class ContratoPersonalServiceImp implements ContratoPersonalService{

    @Autowired
    ContratoPersonalRepository contratoPersonalRepository;

    @Override
    public List<ContratoPersonal> getContratosEmpresas(Long idEmpresa){
        List<ContratoPersonal> contratoPersonal = contratoPersonalRepository.findByIdEmpresa(idEmpresa)
                .stream()
                .map( contratoPersonalEnt ->{
                    //System.out.println("getContribuyentesByRut: " + contribuyenteEntity);
                    return new ContratoPersonal(
                            contratoPersonalEnt.getIdContratoPersonal(),
                            contratoPersonalEnt.getIdEmpresa(),
                            contratoPersonalEnt.getIdContratoPersonal(),
                            contratoPersonalEnt.getFechaInicio(),
                            contratoPersonalEnt.getFechaTermino(),
                            contratoPersonalEnt.getFechaIngreso(),
                            contratoPersonalEnt.getDiasVacaciones(),
                            new Contribuyente(
                                    contratoPersonalEnt.getContribuyenteEntity().getIdContribuyente(),
                                    contratoPersonalEnt.getContribuyenteEntity().getRut(),
                                    contratoPersonalEnt.getContribuyenteEntity().getRazonSocial(),
                                    contratoPersonalEnt.getContribuyenteEntity().getGiro()
                            )
                    );
                }).collect(Collectors.toList());

        //System.out.println("CONTRATO PERSONAL");
        //System.out.println(contratoPersonal + "\n");

        return contratoPersonal;
    }

}
