package com.chileregion.demoMsSql.persistance.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity(name="Empresa")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="IdEmpresa")
    private Long idEmpresa;
    @NonNull
    @Column(nullable = false, unique = true)
    //@JMapAccessor(get="RUT")
    private String RUT;
    @NonNull
    @Column(name="RazonSocial", nullable = false, unique = true)
    //@JMapAccessor(get="RazonSocial")
    private String RazonSocial;
    @Column(name="Giro", nullable = false, unique = true)
    private String Giro;

   // public EmpresaEntity(){ super(); }

/******
    @OneToOne(mappedBy="empresaEntity" , cascade = CascadeType.ALL)
    private DocumentosEntity documentosEntity;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "IdEmpresa")
    private DocumentosEntity documentosEntity;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "IdEmpresa")
    private DocumentosEntity documentosEntity;
    ******/

    /***
    @OneToOne
    @MapsId
    @JoinColumn(name = "IdEmpresa")
    private DocumentosEntity documentosEntity;

    **/

}
