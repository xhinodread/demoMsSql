package com.chileregion.demoMsSql.persistance.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name="Feriados")
@NoArgsConstructor
@AllArgsConstructor
public class FeriadosEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id") private Long id;
    @Column(name="FechaFeriado") private String fechaFeriado;
    @Column(name="Nombre") private String nombre;
}
