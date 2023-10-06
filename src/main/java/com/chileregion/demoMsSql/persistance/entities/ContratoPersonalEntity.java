package com.chileregion.demoMsSql.persistance.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name="ContratoPersonal")
@NoArgsConstructor
@AllArgsConstructor
public class ContratoPersonalEntity {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idContribuyente")
    private ContribuyenteEntity contribuyenteEntity;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="IdContratoPersonal") private Long idContratoPersonal;
    @Column(name="IdContribuyente") private Long idContribuyente;
    @Column(name="IdEmpresa") private Long idEmpresa;
    @Column(name="FechaInicio") private String fechaInicio;
    @Column(name="FechaTermino") private String fechaTermino;
    @Column(name="fechaIngreso") private String fechaIngreso;
    @Column(name="DiasVacaciones") private Integer diasVacaciones;

}
