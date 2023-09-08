package com.chileregion.demoMsSql.utils;

import com.chileregion.demoMsSql.domain.ContratoVacaciones;
import com.chileregion.demoMsSql.persistance.entities.ContratoVacacionesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.temporal.Temporal;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
public class ContratoVacacionesMapper {

    public ContratoVacaciones contratoVacaciones(ContratoVacacionesEntity contratoVacaciones) {
        miFecha();
        ContratoVacaciones registroVacacion = new ContratoVacaciones(
                contratoVacaciones.getIdVacacion(),
                contratoVacaciones.getIdContratoPersonal(),
                contratoVacaciones.getPeriodo(),
                contratoVacaciones.getDesde(),
                contratoVacaciones.getHasta(),
                diferenciaDias(contratoVacaciones.getDesde(), contratoVacaciones.getHasta())
        );
        return registroVacacion;
    }

    private int diferenciaDias(String desde, String hasta){
        try {
            Date _desde = new SimpleDateFormat("yyyy-mm-dd").parse(desde);
            Date _hasta = new SimpleDateFormat("yyyy-mm-dd").parse(hasta);
            int diffInMillies = (int) Math.abs(_desde.getTime() - _hasta.getTime());
            return (int) TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        }catch (Exception e){
            System.out.println( "Error en conversion de fecha" );
            System.out.println( e );
            return 0;
        }
    }
    private void miFecha()  {
        System.out.println( "miFecha" );
        try{
            Date miFecha = new SimpleDateFormat("yyyy-MM-dd").parse("2023-01-02");
            Date miFecha_hasta = new SimpleDateFormat("yyyy-MM-dd").parse("2023-02-25");

            Calendar calendario= new GregorianCalendar();
            calendario.setTime(miFecha);
            //movemos el ccalendario
            calendario.add(Calendar.DATE,23);

            System.out.println( miFecha );
            System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(calendario.getTime()));

            Iterator<Date> i = new DateIterator(miFecha, miFecha_hasta);
            while (i.hasNext()) {
                Date date = i.next();
                System.out.println("- " + date);
            }

        }catch (Exception e){
            System.out.println( "Error en miFecha" );
            System.out.println( e );
        }
    }

}
