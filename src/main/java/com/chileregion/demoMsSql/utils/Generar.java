package com.chileregion.demoMsSql.utils;

import org.springframework.stereotype.Component;

@Component
public class Generar {

    public int[] numeros(){
        int[] nro = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        return nro;
    }

    public String[] meses(){
        String[] meses = new String[]{"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        return meses;
    }

    public String mesesLlamada(int mes){

        String[] meses = new String[]{"mes_enero", "mes_febrero", "mes_marzo", "mes_abril", "mes_mayo", "mes_junio", "mes_julio", "mes_agosto", "mes_septiembre", "mes_octubre", "mes_noviembre", "mes_diciembre"};
        return meses[mes];
    }

}
