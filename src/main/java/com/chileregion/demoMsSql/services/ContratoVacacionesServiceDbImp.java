package com.chileregion.demoMsSql.services;

import com.chileregion.demoMsSql.domain.ContratoVacaciones;
import com.chileregion.demoMsSql.persistance.entities.ContratoVacacionesEntity;
import com.chileregion.demoMsSql.persistance.repository.ContratoVacacionesRepository;
import com.chileregion.demoMsSql.utils.ContratoVacacionesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("BDCONTRATOVACIONES")
public class ContratoVacacionesServiceDbImp implements ContratoVacacionesService {
    @Autowired
    ContratoVacacionesRepository contratoVacacionesRepository;
    @Autowired
    ContratoVacacionesMapper contratoVacacionesMapper;
    @Override
    public ContratoVacaciones getContratoVacaciones(Long idContratoPersonal){
        ContratoVacacionesEntity contratoVacacionesEntity = contratoVacacionesRepository.findByIdContratoPersonal(idContratoPersonal);
        return contratoVacacionesMapper.contratoVacaciones(contratoVacacionesEntity);
    }

    @Override
    public String setContratoVacaciones(ContratoVacaciones contratoVacaciones) {

        ContratoVacacionesEntity laVacacion = new ContratoVacacionesEntity(
                null,
                contratoVacaciones.getIdContratoPersonal(),
                contratoVacaciones.getPeriodo(),
                contratoVacaciones.getDesde(),
                contratoVacaciones.getHasta()
        );
        contratoVacacionesRepository.save(laVacacion);

        return null;
    }
}

