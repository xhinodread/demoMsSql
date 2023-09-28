package com.chileregion.demoMsSql.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImpUnicoSegCat {
    private String desde;
    private String hasta;
    private String factor;
    private String rebaja;
    private String exento;

}
