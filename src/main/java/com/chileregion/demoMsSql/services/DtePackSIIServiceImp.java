package com.chileregion.demoMsSql.services;

import com.chileregion.demoMsSql.domain.DtePackSII;
import com.chileregion.demoMsSql.persistance.entities.DtePackSIIEntity;
import com.chileregion.demoMsSql.persistance.repository.DtePackSIIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("DBPACKDTESII")
public class DtePackSIIServiceImp implements DtePackSIIService {

    @Autowired
    DtePackSIIRepository dtePackSIIRepository;
    @Override
    public DtePackSII getDtePackSii(Long idDtePackSII) {
        Optional<DtePackSIIEntity> getDteSii = Optional.ofNullable(dtePackSIIRepository.findByIdDteCabecera(idDtePackSII));
        return new DtePackSII(
            getDteSii.get().getIdDtePackSII(),
            getDteSii.get().getFechaIngreso(),
            getDteSii.get().getTrackId(),
            getDteSii.get().getIdDteCabecera(),
            getDteSii.get().getEstado(),
            getDteSii.get().getAceptado()
        );
    }

    @Override
    public Long delDtePackSii(Long idDtePackSII) {
        if(false){
            System.out.println("delDtePackSii in : " + idDtePackSII );
            Long borrado =  dtePackSIIRepository.deleteByIdDteCabecera(idDtePackSII);
            System.out.println("delDtePackSii out : " + borrado );
            return borrado;
        }else{
            DtePackSIIEntity getDteSii = dtePackSIIRepository.findByIdDteCabecera(idDtePackSII);
            System.out.println("delDtePackSii out : " + getDteSii );
            return getDteSii == null ? 0L : 1L;
        }
    }
}
