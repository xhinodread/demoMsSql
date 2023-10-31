package com.chileregion.demoMsSql.services;

import com.chileregion.demoMsSql.domain.DtePackSII;

public interface DtePackSIIService {

    DtePackSII getDtePackSii(Long idDtePackSII);
    Long delDtePackSii(Long idDtePackSII);

}
