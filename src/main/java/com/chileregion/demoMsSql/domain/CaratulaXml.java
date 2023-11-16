package com.chileregion.demoMsSql.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CaratulaXml {
    String rutEmisor;
    String rutEnvia;
    String rutReceptor;
    String fchResol;
    String nroResol;
    String tmstFirmaEnv;
    String tpoDTE;
    String nroDTE;
}
