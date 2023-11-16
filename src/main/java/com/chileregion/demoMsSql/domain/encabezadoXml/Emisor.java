package com.chileregion.demoMsSql.domain.encabezadoXml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Emisor {
    String rUTEmisor;
    String rznSoc;
    String giroEmis;
    String acteco;
    String dirOrigen;
    String cmnaOrigen;
}
