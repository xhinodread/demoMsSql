package com.chileregion.demoMsSql.domain.encabezadoXml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdDoc {
   String tipoDTE;
   String folio;
   String fchEmis;
   String fchVenc;
}
