package com.chileregion.demoMsSql.utils;

import com.chileregion.demoMsSql.domain.ImpUnicoSegCat;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProcesarImpUnico {

    public ImpUnicoSegCat make(Elements hijos){

        String desde = hijos.get(1).text();
        int indexDesde = desde.indexOf('$');
        //System.out.println("$ "+ indexDesde);
        if( indexDesde <0){
            desde = "0.0";
        }else{
            desde = desde.substring(1).trim().replace(".", "").replace(",", ".");
        }
        String hasta = hijos.get(2).text();
        int indexHasta = hasta.indexOf('$');
        //System.out.println("$ "+ indexHasta);
        if( indexHasta <0){
            hasta = "999999999.00";
        }else{
            hasta = hasta.substring(1).trim().replace(".", "").replace(",", ".");
        }
        String factor = hijos.get(3).text();
        int indexFactor = factor.indexOf("Exento");
        //System.out.println("Exento "+ indexFactor);
        if( indexFactor >=0){
            factor = "0.0";
        }else{
            factor = factor.trim().replace(",", ".");
        }
        String rebaja = hijos.get(4).text();
        int indexRebaja = rebaja.indexOf('$');
        //System.out.println("$ "+ indexRebaja);
        if( indexRebaja <0){
            rebaja = "0.0";
        }else{
            rebaja = rebaja.substring(1).trim().replace(".", "").replace(",", ".");
        }
        String tasa = hijos.get(5).text();
        int indexTasa = tasa.indexOf("Exento");
        //System.out.println("Exento "+ indexTasa);
        if( indexTasa >=0){
            tasa = "0.0";
        }else{
            tasa = tasa.trim().replace(",", ".").replace("MÁS DE ", "");
        }

        return new ImpUnicoSegCat(
                desde,
                hasta,
                factor,
                rebaja,
                tasa
        );
    }


}
