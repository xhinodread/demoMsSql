package com.chileregion.demoMsSql.persistance.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity(name="DteCabecera")
@NoArgsConstructor
@AllArgsConstructor
public class DocumentosEntity {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idEmpresa")
    private EmpresaEntity empresaEntity;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idContribuyente")
    private ContribuyenteEntity contribuyenteEntity;
    @OneToMany
    @JoinColumn(name = "idDteCabecera")
    private List<DocumentoDetallesEntity> documentoDetallesEntity;
    @OneToMany
    @JoinColumn(name = "idDteCabecera")
    private List<DocumentoReferenciasEntity> documentoReferenciasEntity;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="IdDteCabecera")
    private Long idDteCabecera;
    @Column(name="IdTipoDte")
    private String tipo;
    /*****
     * @Column(name="rut")
    private String rut;
    *****/
    @Column(name="IdEmpresa")
    private Long IdEmpresa;
    @Column(name="Folio")
    private String folio;
    @Column(name="FechaEmision")private String fecha_emision;
    @Column(name="IdContribuyente")private String receptor;
    @Column(name="DireccionReceptor")private String razon_receptor;
    @Column(name="MontoTotal")private Long monto_total;
    @Column(name="MontoReal")private Long monto_real;
    @Column(name="Estado")private String estado;


    public EmpresaEntity getEmpresaEntity() {
        return empresaEntity;
    }
    public void setEmpresaEntity(EmpresaEntity empresaEntity){
        this.empresaEntity = empresaEntity;
    }


    /****

     @OneToOne(mappedBy="documentosEntity" , cascade = CascadeType.ALL)
     private EmpresaEntity empresaEntity;


     *
     *
     *
     *
     @PrimaryKeyJoinColumn
    / *****/
}
