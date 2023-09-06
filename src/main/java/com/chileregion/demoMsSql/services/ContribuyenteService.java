package com.chileregion.demoMsSql.services;

import com.chileregion.demoMsSql.domain.Contribuyente;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ContribuyenteService {
    public List<Contribuyente> getContribuyentes();
    public List<Contribuyente> getContribuyentesWeb(int pagina);
    public int cantidadContribuyentes();
    public List<Contribuyente> getContribuyentesByRazonSocial(String razonSocial, int pagina);
    public List<Contribuyente> getContribuyentesByRut(String Rut);

}
