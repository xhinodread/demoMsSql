package com.chileregion.demoMsSql.domain.encabezadoXml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Totales {
    String mntNeto;
    String mntExe;
    String tasaIVA;
    String iVA;
    String mntTotal;
}
