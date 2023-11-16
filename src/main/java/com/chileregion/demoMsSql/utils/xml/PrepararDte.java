package com.chileregion.demoMsSql.utils.xml;

import com.chileregion.demoMsSql.domain.CaratulaXml;
import com.chileregion.demoMsSql.domain.Contribuyente;
import com.chileregion.demoMsSql.domain.Documentos;
import com.chileregion.demoMsSql.domain.Empresa;
import org.springframework.stereotype.Component;

public class PrepararDte {

    private Empresa emisor;
    private Contribuyente receptor;
    //private CaratulaXml caratulaXml1;
    public PrepararDte(Documentos documentos){
        this.emisor = documentos.getEmpresa();
        this.receptor = documentos.getContribuyente();
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

}
