package com.chileregion.demoMsSql.services;

import com.chileregion.demoMsSql.domain.Feriados;
import com.chileregion.demoMsSql.persistance.entities.FeriadosEntity;
import com.chileregion.demoMsSql.persistance.repository.FeriadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service("FERIADOS")
public class FeriadosServiceImp implements FeriadosService {

    @Autowired
    FeriadosRepository feriadosRepository;

    public List<Feriados> getFeriados(String desde, String hasta){
        return feriadosRepository.findFeriados(desde, hasta)
            .stream()
            .map(valorFeriados->{
                return new Feriados(
                    valorFeriados.getId(),
                    valorFeriados.getFechaFeriado(),
                    valorFeriados.getNombre()
                );
            })
            .collect(Collectors.toList());
    }


}
