package com.chileregion.demoMsSql.utils;

import com.chileregion.demoMsSql.domain.Empresa;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
public class EmpresaUtils {

    public static boolean selectFiltroEmpresas(Empresa empresa) {
        Predicate <Empresa> p1 = (Empresa e) -> e.getId() == 1;
        Predicate <Empresa> p2 = (Empresa e) -> e.getId() == 2;
        Predicate <Empresa> p3 = (Empresa e) -> e.getId() == 3;
        Predicate <Empresa> p4 = (Empresa e) -> e.getId() == 9;
        Predicate <Empresa> total = p1.or(p2).or(p3).or(p4);
        return total.test(empresa);
    }

    public static boolean selectFiltroEmpresasDtes(Empresa empresa) {
        Predicate <Empresa> p1 = (Empresa e) -> e.getId() == 1;
        Predicate <Empresa> p2 = (Empresa e) -> e.getId() == 2;
        Predicate <Empresa> p3 = (Empresa e) -> e.getId() == 3;
        Predicate <Empresa> p4 = (Empresa e) -> e.getId() == 7;
        Predicate <Empresa> p5 = (Empresa e) -> e.getId() == 9;
        Predicate <Empresa> p6 = (Empresa e) -> e.getId() == 13;
        Predicate <Empresa> p7 = (Empresa e) -> e.getId() == 15;
        Predicate <Empresa> total = p1.or(p2).or(p3).or(p4).or(p5).or(p6).or(p7);
        return total.test(empresa);
    }

}
