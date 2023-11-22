package com.chileregion.demoMsSql.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReferenciaXml {
    String nroLinRef;
    String tpoDocRef;
    String folioRef;
    String fchRef;
    String razonRef;
}
