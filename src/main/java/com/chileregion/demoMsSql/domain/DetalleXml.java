package com.chileregion.demoMsSql.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleXml {
    String nroLinDet;
    String nmbItem;
    String qtyItem;
    String unmdItem;
    String prcItem;
    String montoItem;
}
