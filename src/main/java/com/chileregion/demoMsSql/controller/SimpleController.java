package com.chileregion.demoMsSql.controller;

import com.chileregion.demoMsSql.domain.Contribuyente;
import com.chileregion.demoMsSql.services.ContribuyenteService;
import com.chileregion.demoMsSql.utils.Generar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SimpleController {

    @Autowired
    @Lazy
    private ContribuyenteService contribuyenteService;
    @Autowired
    @Lazy
    private Generar generar;

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

}
