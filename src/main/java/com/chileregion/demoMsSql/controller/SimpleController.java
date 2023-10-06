package com.chileregion.demoMsSql.controller;

import com.chileregion.demoMsSql.domain.Contribuyente;
import com.chileregion.demoMsSql.domain.Empresa;
import com.chileregion.demoMsSql.services.ContribuyenteService;
import com.chileregion.demoMsSql.services.EmpresaService;
import com.chileregion.demoMsSql.utils.EmpresaUtils;
import com.chileregion.demoMsSql.utils.Generar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class SimpleController {

    @Autowired
    @Lazy
    private ContribuyenteService contribuyenteService;
    @Autowired
    @Lazy
    private Generar generar;

    @Autowired
    @Lazy
    private EmpresaService empresaService;

    @GetMapping("/contribuyentes")
    public String getAllContribuyentes(Model model){
        //List<Contribuyente> contribuyentes = contribuyenteService.getContribuyentes();
        int nroContribuyentes = contribuyenteService.cantidadContribuyentes();
        int[] nro = generar.numeros(); // utilidad para hardcodear las paginas

        List<Contribuyente> contribuyentes = contribuyenteService.getContribuyentesWeb(0);

        model.addAttribute("contribuyentes", contribuyentes);
        model.addAttribute("nroContribuyentes", nroContribuyentes);
        model.addAttribute("nro", nro);
        return "contribuyentesweb";
    }

    /****
    @GetMapping("/contribuyentesweb/{pag}")
    public String getAllContribuyentesweb(@PathVariable("pag") int pag, Model model){
        // System.out.println("pag: " + pag);

        List<Contribuyente> contribuyentes = contribuyenteService.getContribuyentesWeb(pag);
        model.addAttribute("contribuyentes",contribuyentes);
        return "contribuyentesweb";
    }
    *****/


    @GetMapping("/jmapping")
    public String jmapping(Model model) {
        model.addAttribute("meses", generar.meses());
        return "jmapping";
    }


    @GetMapping("/main")
    public String mainErp(){

        return "main_erp";
    }
    @GetMapping("/vacaciones")
    public String mainVacaciones(Model model){

        //List<Empresa> empresas = empresaService.getEmpresas();
        // .filter(dato-> dato.getId() == 1 || dato.getId() == 2 )
        List<Empresa> empresas_2 = empresaService.getEmpresas()
                .stream()
                .filter(EmpresaUtils::selectFiltroEmpresas)
                .sorted(Comparator.comparing(Empresa::getRazonSocial).thenComparing(Empresa::getRazonSocial))
                .collect(Collectors.toList());

        model.addAttribute("empresas", empresas_2);
        return "main_vacaciones";
    }



}
