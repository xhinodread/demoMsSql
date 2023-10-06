package com.chileregion.demoMsSql.persistance.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Entity(name="ContratoVacaciones")
@NoArgsConstructor
@AllArgsConstructor
public class ContratoVacacionesEntity {
    // IdVacacion  IdContratoPersonal Periodo     Desde                   Hasta

    /*****
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idContribuyente")
    private ContribuyenteEntity contribuyenteEntity;
    ****/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="IdVacacion")
    private Long idVacacion;
    @NonNull
    @Column(name="IdContratoPersonal", nullable = false, unique = true)
    private Long idContratoPersonal;
    @NonNull
    @Column(name="Periodo", nullable = false, unique = true)
    private String periodo;
    @NonNull
    @Column(name="Desde", nullable = false, unique = true)
    private String desde;
    @NonNull
    @Column(name="Hasta", nullable = false, unique = true)
    private String hasta;

    @Column(name="dias")
    private Integer dias;



}
