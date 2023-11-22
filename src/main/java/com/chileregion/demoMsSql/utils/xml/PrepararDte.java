package com.chileregion.demoMsSql.utils.xml;

import com.chileregion.demoMsSql.domain.*;
import com.chileregion.demoMsSql.domain.encabezadoXml.Emisor;
import com.chileregion.demoMsSql.domain.encabezadoXml.IdDoc;
import com.chileregion.demoMsSql.domain.encabezadoXml.Receptor;
import com.chileregion.demoMsSql.domain.encabezadoXml.Totales;

import java.util.ArrayList;
import java.util.List;

public class PrepararDte {

    private Empresa emisor;
    private Contribuyente receptor;
    private IdDoc idDoc;
    private Emisor emisor1;
    private Receptor receptor1;
    private Totales totales;
    private String montoIva;
    private Integer montoNeto;
    private List<DocumentoDetalles> documentoDetalles;
    private List<DetalleXml> listaDocumentoDetalles;
    private List<DocumentoReferencias> documentoReferencias;
    private List<ReferenciaXml> listaDocumentoReferencia;

    public PrepararDte(Documentos documentos){
        this.emisor = documentos.getEmpresa();
        this.receptor = documentos.getContribuyente();
        this.idDoc = new IdDoc(
                "33",
                documentos.getFolio(),
                documentos.getFecha_emision(),
                documentos.getFecha_emision()
        );
        this.emisor1 = new Emisor(
                emisor.getRUT(),
                emisor.getRazonSocial(),
                emisor.getGiro(),
                "Acteco", "Direccion", "Comuna"
        );
        this.receptor1 = new Receptor(
                receptor.getRUT(),
                receptor.getRazonSocial(),
                receptor.getGiro(),
                "Direccion", "Comuna"
        );

        this.montoIva = documentos.getMontoIva();
        this.montoNeto = documentos.getMonto_total().intValue();
        this.preparaTotales();

        this.documentoDetalles = documentos.getDocumentoDetalles();
        this.preparaDocumentoDetalles();

        this.documentoReferencias = documentos.getDocumentoReferencias();
    }

    private void preparaTotales(){
        int montoNeto = this.montoNeto - Math.round(Float.parseFloat(this.montoIva));
        this.totales = new Totales(
                Integer.toString(montoNeto),
                "0", "19",
                this.montoIva,
                this.montoNeto.toString()
        );
    }
    private void preparaDocumentoDetalles(){
        List<DocumentoDetalles> documentoDetalles = this.documentoDetalles;
        //System.out.println( "DocumentoDetalles" );
        List<DetalleXml> detalleXml1= new ArrayList<>();
        documentoDetalles.forEach( valorItem ->{
            detalleXml1.add(
                new DetalleXml(
                    "0",
                    valorItem.getNombreItem(),
                    String.valueOf( valorItem.getCantidad()),
                    valorItem.getUnidadMedida(),
                    String.valueOf(valorItem.getPrecioUnitario()),
                    String.valueOf(valorItem.getMontoItem())
                )
            );
        });
        this.listaDocumentoDetalles = detalleXml1;
    }

    private void preparaReferencias(){

    }
    public CaratulaXml caratulaXml(){
        // "16388980-3"
        return new CaratulaXml(
                emisor.getRUT(),
                "14385302-3",
                receptor.getRUT(),
                "2014-08-22",
                "80",
                "2023-05-22T12:47:05",
                "33" ,
                "1"
        );
    }
    public IdDoc encabezadoIdDoc(){ return this.idDoc; }
    public Emisor encabezadoEmisor(){ return this.emisor1; }
    public Receptor encabezadoReceptor(){ return this.receptor1; }
    public Totales encabezadoTotales(){ return this.totales; }
    public List<DetalleXml> documentoDetalles(){return this.listaDocumentoDetalles;}
    public List<ReferenciaXml> documentoReferencia(){
        System.out.println(this.documentoReferencias);

        List<ReferenciaXml> referenciaXml1 =new ArrayList<>();
        this.documentoReferencias.forEach(valor->{
            referenciaXml1.add(
                    new ReferenciaXml(
                            "0",
                            valor.getTipoDocumentoReferencia(),
                            valor.getFolioReferencia(),
                            valor.getFechaReferencia(),
                            valor.getRazonReferencia()
                    )
            );
        });

        return this.listaDocumentoReferencia;
    }
}
