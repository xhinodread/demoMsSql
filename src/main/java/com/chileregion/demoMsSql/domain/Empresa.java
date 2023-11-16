package com.chileregion.demoMsSql.domain;

import com.googlecode.jmapper.annotations.JMap;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//@Table(name="Empresa")
//@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Empresa {
    @JMap
    private Long id;
    @JMap
    private String RUT;
    @JMap
    private String RazonSocial;
    private String Giro;

    //public Empresa(){ super(); }

}
