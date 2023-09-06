package com.chileregion.demoMsSql.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentoDetalles {
    // IdDteDetalle	IdDteCabecera	NombreItem	Cantidad	UnidadMedida	PrecioUnitario	Descuento	MontoDescuento	MontoItem
    private Long idDteDetalle;
    private Long idDteCabecera;
    private String nombreItem;
    private int cantidad;
    private String unidadMedida;
    private int precioUnitario;
    private String descuento;
    private int montoDescuento;
    private int montoItem;
}
