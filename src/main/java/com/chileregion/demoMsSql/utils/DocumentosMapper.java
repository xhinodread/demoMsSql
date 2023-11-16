package com.chileregion.demoMsSql.utils;

import com.chileregion.demoMsSql.domain.*;
import com.chileregion.demoMsSql.persistance.entities.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Component
public class DocumentosMapper {
    public static Documentos mapper(DocumentosEntity documentosEntity){
        return new Documentos(
            documentosEntity.getIdDteCabecera(),
            documentosEntity.getTipo(),
            documentosEntity.getIdEmpresa(),
            documentosEntity.getFolio(),
            documentosEntity.getFecha_emision(),
            documentosEntity.getReceptor(),
            documentosEntity.getRazon_receptor(),
            documentosEntity.getMonto_total(),
            documentosEntity.getMonto_real(),
            documentosEntity.getEstado(),
            documentosEntity.getIdOperacion(),
            documentosEntity.getMontoIva(),
            empresaMapper(documentosEntity.getEmpresaEntity()),
            contribuyenteMapper(documentosEntity.getContribuyenteEntity()),
            documentoDetallesMapper(documentosEntity.getDocumentoDetallesEntity()),
            documentoReferenciasMapper(documentosEntity.getDocumentoReferenciasEntity())
        );
    }

    private static Empresa empresaMapper(EmpresaEntity empresaEntity){
        return new Empresa(
            empresaEntity.getIdEmpresa(),
            empresaEntity.getRUT(),
            empresaEntity.getRazonSocial(),
                empresaEntity.getGiro()
        );
    }

    private static Contribuyente contribuyenteMapper(ContribuyenteEntity contribuyenteEntity){
        return new Contribuyente(
                contribuyenteEntity.getIdContribuyente(),
                contribuyenteEntity.getRut(),
                contribuyenteEntity.getRazonSocial(),
                contribuyenteEntity.getGiro()
        );
    }

    private static List<DocumentoDetalles> documentoDetallesMapper(List<DocumentoDetallesEntity> documentoDetallesEntity){
        List<DocumentoDetalles> documentoDetalles= new ArrayList<DocumentoDetalles>();
        //System.out.println("documentoDetallesEntity \n");
        documentoDetallesEntity.stream().forEach(documentoDetalle -> {
            //System.out.println(documentoDetalle);
            documentoDetalles.add(
                new DocumentoDetalles(
                    documentoDetalle.getIdDteDetalle(),
                    documentoDetalle.getIdDteCabecera(),
                    documentoDetalle.getNombreItem(),
                    documentoDetalle.getCantidad(),
                    documentoDetalle.getUnidadMedida(),
                    documentoDetalle.getPrecioUnitario(),
                    documentoDetalle.getDescuento(),
                    documentoDetalle.getMontoDescuento(),
                    documentoDetalle.getMontoItem()
                )
            );
        });
        //System.out.println("\n" + documentoDetalles);
        return documentoDetalles;
    }

    private static List<DocumentoReferencias> documentoReferenciasMapper(List<DocumentoReferenciasEntity> documentoReferenciasEntity ){
        List<DocumentoReferencias> documentoReferencias = new ArrayList<>();
        documentoReferenciasEntity.stream().forEach(documentoReferencia ->{
            documentoReferencias.add(
                new DocumentoReferencias(
                    documentoReferencia.getIdDteReferencia(),
                    documentoReferencia.getIdDteCabecera(),
                    documentoReferencia.getFechaReferencia(),
                    documentoReferencia.getRazonReferencia(),
                    documentoReferencia.getTipoDocumentoReferencia(),
                    documentoReferencia.getFolioReferencia(),
                    documentoReferencia.getCodigoReferencia()
                )
            );
        });
        return documentoReferencias;
    }
    private void mapeo(){
        /**** EJEMPLO DE COMO RECORRER UN OBJETO ****/
        DocumentosEntity documentoX = new DocumentosEntity(); /// vacio
        Documentos documento = new Documentos();
        System.out.println("keys: " );

        Class<?> uClass = documento.getClass();
        Class<?> uClassX = documentoX.getClass();
        Field[] uFields = uClass.getDeclaredFields();
        Field[] uFieldsX = uClassX.getDeclaredFields();
        //System.out.println(uFields.toString() );

        for (Field f : uFieldsX ) {
            // System.out.print("Field name:\t" + f.getName() + "\t");
            for (Field fX : uFields ) {
                //    System.out.print("Field name:\t" + fX.getName() + "\t");

                if( f.getName() == fX.getName()){
                    try {
                        f.setAccessible(true);
                        // Object obj = f.get(f.getName());
                        System.out.print("AGREGAR Field: " + f.getName() + " == " + fX.getName());
                        //  System.out.print("VALOR Field: " + obj );
                        //  documentoX.setIdDteCabecera( obj );
                        System.out.print("\t");
                    }catch (IllegalArgumentException e) {
                        e.printStackTrace();
                        System.out.print("ERROR");
                    }
                }
            }
        }
    }

}
