package com.chileregion.demoMsSql.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contribuyente {
    private long IdContribuyente;
    private String RUT;
    private String RazonSocial;
    private String Giro;
}
