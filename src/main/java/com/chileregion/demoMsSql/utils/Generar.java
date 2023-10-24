package com.chileregion.demoMsSql.utils;

import com.chileregion.demoMsSql.domain.Feriados;
import com.chileregion.demoMsSql.services.FeriadosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Generar {

    @Autowired
    FeriadosService feriadosService;

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

    public Integer calcularDias(String desde, String hasta){
        System.out.println("calcularDias");
        System.out.println(desde);
        System.out.println(hasta);

        Integer dias = 0;
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");


        //System.out.println("feriados");

        List<Date> fechasFeriadosX = feriadosService.getFeriados("", "")
            .stream()
            .map(valor->{
                try {
                    Date laFecha = new SimpleDateFormat("yyyy-MM-dd").parse(valor.getFechaFeriado());
                    return laFecha;
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }).toList();

        /*******
        List<Date> fechasFeriados =new ArrayList<Date>();
        List<Feriados> feriados = feriadosService.getFeriados("", "");
        //System.out.println(feriados);
        for (Feriados feriado : feriados) {
            try {
                Date dateFeriado = new SimpleDateFormat("yyyy-MM-dd").parse(feriado.getFechaFeriado());
                //System.out.println(dateFeriado);
                fechasFeriados.add(dateFeriado);
            } catch (ParseException e) {
                System.out.println("ParseException");
                System.out.println(e);
                throw new RuntimeException(e);
            }
        }
        *******/
        //System.out.println(fechasFeriados);
        //System.out.println("**************************");
        try {
            Date date1 = sdf.parse(desde);
            //System.out.println(date1);
            Date date2 = sdf.parse(hasta);
            //System.out.println(date2);
            Calendar start = Calendar.getInstance();
            start.setTime(date1);
            Calendar end = Calendar.getInstance();
            end.setTime(date2);

            //System.out.println("dates");

            //for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
            for (Date date = start.getTime(); !start.after(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
                //System.out.println(date);
                //System.out.println(dayofWeek(date));
                //System.out.println("busca fecha");
                //System.out.println( fechasFeriados.contains(date) );

                if( dayofWeek(date) > 1 && dayofWeek(date) < 7 ){
                        if( !fechasFeriadosX.contains(date) ){
                        //if( !fechasFeriados.contains(date) ){
                        dias++;
                    }
                }
            }

            return dias;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public int dayofWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

}
