package com.chileregion.demoMsSql.persistance.repository;

import com.chileregion.demoMsSql.persistance.entities.FeriadosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FeriadosRepository extends JpaRepository<FeriadosEntity, Long> {

    //@Query("SELECT c FROM Contribuyente c")
    @Query(
            value ="SELECT TOP 30 * FROM ECC_MAIN.dbo.Feriados WHERE YEAR(FechaFeriado) IN (2022, 2023) ORDER BY FechaFeriado",
            nativeQuery = true
    )
    List<FeriadosEntity> findFeriados(String desde, String hasta);

}
