package com.chileregion.demoMsSql.services;

import com.chileregion.demoMsSql.domain.ContratoVacaciones;
import com.chileregion.demoMsSql.domain.Contribuyente;
import com.chileregion.demoMsSql.persistance.entities.ContratoVacacionesEntity;
import com.chileregion.demoMsSql.persistance.entities.ContribuyenteEntity;
import com.chileregion.demoMsSql.persistance.repository.ContratoVacacionesRepository;
import com.chileregion.demoMsSql.utils.ContratoVacacionesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("BDCONTRATOVACIONES")
public class ContratoVacacionesServiceDbImp implements ContratoVacacionesService {
    @Autowired
    ContratoVacacionesRepository contratoVacacionesRepository;
    @Autowired
    ContratoVacacionesMapper contratoVacacionesMapper;
    @Override
    public List<ContratoVacaciones> getContratoVacaciones(Long idContratoPersonal){
        // ContratoVacacionesEntity contratoVacacionesEntity = contratoVacacionesRepository.findByIdContratoPersonal(idContratoPersonal);

        List<ContratoVacaciones> listaVacaciones = contratoVacacionesRepository.findByIdContratoPersonal(idContratoPersonal)
                .stream()
                .map( contratoVacacionesEntity ->{
                    return new ContratoVacaciones(
                           contratoVacacionesEntity.getIdVacacion(),
                           contratoVacacionesEntity.getIdContratoPersonal(),
                           contratoVacacionesEntity.getPeriodo(),
                           contratoVacacionesEntity.getDesde(),
                           contratoVacacionesEntity.getHasta(),
                           contratoVacacionesEntity.getDias()
                   );
                }).collect(Collectors.toList());
        return listaVacaciones;

        /***
        ContratoVacaciones vacacion = contratoVacacionesMapper.contratoVacaciones(contratoVacacionesEntity);
        listaVacaciones = (List<?>) vacacion;
        return (List<ContratoVacaciones>) listaVacaciones;
        ****/
    }

    @Override
    public String setContratoVacaciones(ContratoVacaciones contratoVacaciones) {

        ContratoVacacionesEntity laVacacion = new ContratoVacacionesEntity(
                null,
                contratoVacaciones.getIdContratoPersonal(),
                contratoVacaciones.getPeriodo(),
                contratoVacaciones.getDesde(),
                contratoVacaciones.getHasta(),
                contratoVacaciones.getDias()
                );
        contratoVacacionesRepository.save(laVacacion);

        return null;
    }
}

