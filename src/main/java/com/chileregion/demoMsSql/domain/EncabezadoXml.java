package com.chileregion.demoMsSql.domain;

import com.chileregion.demoMsSql.domain.encabezadoXml.Emisor;
import com.chileregion.demoMsSql.domain.encabezadoXml.IdDoc;
import com.chileregion.demoMsSql.domain.encabezadoXml.Receptor;
import com.chileregion.demoMsSql.domain.encabezadoXml.Totales;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EncabezadoXml {
    IdDoc idDoc;
    Emisor emisor;
    Receptor receptor;
    Totales totales;
}
