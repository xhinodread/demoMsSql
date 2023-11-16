package com.chileregion.demoMsSql.domain.encabezadoXml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Receptor {
    String rUTRecep;
    String RznSocRecep;
    String giroRecep;
    String dirRecep;
    String cmnaRecep;
}
