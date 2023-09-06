package com.chileregion.demoMsSql.persistance.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

@Data
@Entity(name="DteReferencia")
@NoArgsConstructor
@AllArgsConstructor
public class DocumentoReferenciasEntity {
    // IdDteReferencia	IdDteCabecera	FechaReferencia	RazonReferencia	TipoDocumentoReferencia	FolioReferencia	CodigoReferencia	IdDteCabeceraReferenciado

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="IdDteReferencia")
    private Long idDteReferencia;
    @Column(name="IdDteCabecera")private Long idDteCabecera;
    @Column(name="FechaReferencia")private String fechaReferencia;
    @Column(name="RazonReferencia")private String razonReferencia;
    @Column(name="TipoDocumentoReferencia")private String tipoDocumentoReferencia;
    @Column(name="FolioReferencia")private String folioReferencia;
    @Column(name="CodigoReferencia")private String codigoReferencia;

    //@Column(name="IdDteCabeceraReferenciado")private String idDteCabeceraReferenciado;


}
