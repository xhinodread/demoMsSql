package com.chileregion.demoMsSql.services;

import com.chileregion.demoMsSql.domain.Contribuyente;
import com.chileregion.demoMsSql.persistance.entities.ContribuyenteEntity;
import com.chileregion.demoMsSql.persistance.repository.ContribuyenteRepository;
import com.googlecode.jmapper.JMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("BDCONTR")
public class ContribuyenteServiceDbImp implements ContribuyenteService {

    @Autowired
    ContribuyenteRepository contribuyenteRepository;
    @Override
    public int cantidadContribuyentes(){
        return (int) contribuyenteRepository.count();
    }
    @Override
    public List<Contribuyente> getContribuyentesByRazonSocial(String razonSocial,int pagina) {
        Sort sort = Sort.by(Sort.Direction.ASC, "razonSocial");
        Pageable page = PageRequest.of(pagina,10, sort);
        List<Contribuyente> contribuyentes = contribuyenteRepository.findByRazonSocialLike("%"+razonSocial+"%", page)
            .stream()
            .map( contribuyenteEntity ->{
                //System.out.println("contribuyenteEntity: " + contribuyenteEntity);
                return new Contribuyente(
                        contribuyenteEntity.getIdContribuyente(),
                        contribuyenteEntity.getRut(),
                        contribuyenteEntity.getRazonSocial(),
                        contribuyenteEntity.getGiro()
                );
        }).collect(Collectors.toList());

        return contribuyentes;
    }
    @Override
    public List<Contribuyente> getContribuyentesByRut(String Rut) {
        List<Contribuyente> contribuyentes = contribuyenteRepository.findByRut(Rut.replace("-", ""))
           .stream()
           .map( contribuyenteEntity ->{
                //System.out.println("getContribuyentesByRut: " + contribuyenteEntity);
                return new Contribuyente(
                    contribuyenteEntity.getIdContribuyente(),
                    contribuyenteEntity.getRut(),
                    contribuyenteEntity.getRazonSocial(),
                    contribuyenteEntity.getGiro()
                );
           }).collect(Collectors.toList());
        return contribuyentes;
    }
    @Override
    public List<Contribuyente> getContribuyentes() {
        JMapper<Contribuyente, ContribuyenteEntity> jmapper = new JMapper<>(Contribuyente.class, ContribuyenteEntity.class);

        List<Contribuyente> contribuyentes = contribuyenteRepository.findAll()
                .stream()
                .map( contribuyenteEntity ->{
                     // System.out.println("contribuyenteEntity: " + contribuyenteEntity);
                    /***/
                    return new Contribuyente(
                            contribuyenteEntity.getIdContribuyente(),
                            contribuyenteEntity.getRut(),
                            contribuyenteEntity.getRazonSocial(),
                            contribuyenteEntity.getGiro()
                    );
                    //return contribuyente;
                    /****/
                   // return new Contribuyente(1, "2", "3", "4");
                }).collect(Collectors.toList());
        return contribuyentes;
    }
    @Override
    public List<Contribuyente> getContribuyentesWeb(int pagina) {
        JMapper<Contribuyente, ContribuyenteEntity> jmapper = new JMapper<>(Contribuyente.class, ContribuyenteEntity.class);

        Sort sort = Sort.by(Sort.Direction.ASC, "razonSocial");
        Pageable page = PageRequest.of(pagina, 10, sort);
        List<Contribuyente> contribuyentes = contribuyenteRepository.findAllWeb(page)
                .stream()
                .map( contribuyenteEntity ->{
                    return new Contribuyente(
                            contribuyenteEntity.getIdContribuyente(),
                            contribuyenteEntity.getRut(),
                            contribuyenteEntity.getRazonSocial(),
                            contribuyenteEntity.getGiro()
                    );
                }).collect(Collectors.toList());
        return contribuyentes;
    }

}
