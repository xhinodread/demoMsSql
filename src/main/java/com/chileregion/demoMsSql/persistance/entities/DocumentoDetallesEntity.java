package com.chileregion.demoMsSql.persistance.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name="DteDetalle")
@NoArgsConstructor
@AllArgsConstructor
public class DocumentoDetallesEntity {
    // IdDteDetalle	IdDteCabecera	NombreItem	Cantidad	UnidadMedida	PrecioUnitario	Descuento	MontoDescuento	MontoItem
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="IdDteDetalle")
    private Long idDteDetalle;

    @Column(name="IdDteCabecera")private Long idDteCabecera;
    @Column(name="NombreItem")private String nombreItem;
    @Column(name="Cantidad")private int cantidad;
    @Column(name="UnidadMedida")private String unidadMedida;
    @Column(name="PrecioUnitario")private int precioUnitario;
    @Column(name="Descuento")private String descuento;
    @Column(name="MontoDescuento")private int montoDescuento;
    @Column(name="MontoItem")private int montoItem;


}
