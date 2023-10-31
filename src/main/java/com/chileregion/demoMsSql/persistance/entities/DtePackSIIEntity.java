package com.chileregion.demoMsSql.persistance.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name="DtePackSII")
@NoArgsConstructor
@AllArgsConstructor
public class DtePackSIIEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="IdDtePackSII") private Long idDtePackSII;
    @Column(name="FechaIngreso") private String fechaIngreso;
    @Column(name="TrackId") private String trackId;
    @Column(name="IdDteCabecera") private Long idDteCabecera;
    @Column(name="Estado") private String estado;
    @Column(name="Aceptado") private String aceptado;


}
