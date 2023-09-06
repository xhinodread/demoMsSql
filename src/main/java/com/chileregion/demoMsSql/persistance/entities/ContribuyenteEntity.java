package com.chileregion.demoMsSql.persistance.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@Data
@Entity(name="Contribuyente")
@NoArgsConstructor
@AllArgsConstructor
public class ContribuyenteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="IdContribuyente")
    private long idContribuyente;
    @NonNull
    @Column(name="RUT", nullable = false, unique = true)
    private String rut;
    @NonNull
    @Column(name="RazonSocial", nullable = false, unique = false)
    private String razonSocial;
    @NonNull
    @Column(nullable = false, unique = false)
    private String Giro;
}
