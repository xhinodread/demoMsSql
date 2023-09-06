package com.chileregion.demoMsSql.utils;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ValidaRutUtil {
    public static Boolean validaRut ( String rut ) {
        //Pattern pattern = Pattern.compile("^(\\d{1,3}(?:\\.\\d{1,3}){2}-[\\dkK])$"); // con punto
        //Pattern pattern = Pattern.compile("^[0-9]+-[0-9kK]{1}$");  // sin punto
        Pattern pattern = Pattern.compile("^(\\d{1,3}(?:\\d{1,3}){2}-[\\dkK])$");  // sin punto
        Matcher matcher = pattern.matcher(rut);
        if ( matcher.matches() == false ) return false;
        String[] stringRut = rut.split("-");
        return stringRut[1].toLowerCase().equals(ValidaRutUtil.dv(stringRut[0]));
    }

    /** (\d{1,3}(?:\.\d{1,3}){2}-[\dkK])
     * Valida el dígito verificador
     */
    public static String dv ( String rut ) {
        Integer M=0,S=1,T=Integer.parseInt(rut);
        for (;T!=0;T=(int) Math.floor(T/=10))
            S=(S+T%10*(9-M++%6))%11;
        return ( S > 0 ) ? String.valueOf(S-1) : "k";
    }
}
