package com.chileregion.demoMsSql.persistance.repository;

import com.chileregion.demoMsSql.persistance.entities.ContribuyenteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface ContribuyenteRepository extends JpaRepository<ContribuyenteEntity, Long> {

    List<ContribuyenteEntity> findAll();
    @Query("SELECT c FROM Contribuyente c")
    List<ContribuyenteEntity> findAllWeb(Pageable pageable);
    List<ContribuyenteEntity> findByRazonSocialLike(String razonSocial, Pageable pageable);
    List<ContribuyenteEntity> findByRut(String Rut);
}
