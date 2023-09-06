package com.chileregion.demoMsSql.controller;

import com.chileregion.demoMsSql.domain.Contribuyente;
import com.chileregion.demoMsSql.domain.DocumentoReferencias;
import com.chileregion.demoMsSql.domain.Documentos;
import com.chileregion.demoMsSql.domain.Empresa;
import com.chileregion.demoMsSql.persistance.entities.ContribuyenteEntity;
import com.chileregion.demoMsSql.services.ContribuyenteService;
import com.chileregion.demoMsSql.services.DocumentoReferenciasService;
import com.chileregion.demoMsSql.services.DocumentosService;
import com.chileregion.demoMsSql.services.EmpresaService;
import com.chileregion.demoMsSql.utils.ValidaRutUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class IndexController {

    @Autowired
    @Lazy
    private EmpresaService empresaService;
    @Autowired
    @Lazy
    private ContribuyenteService contribuyenteService;
    @Autowired
    @Lazy
    private DocumentosService documentosService;
    @Autowired
    private DocumentoReferenciasService documentoReferenciasService;

    @Autowired
    @Lazy
    private ValidaRutUtil validaRutUtil;


    @GetMapping("/")
    public String index(){
        return "Bienvenido";
    }

    @GetMapping("/empresas")
    public ResponseEntity<List<Empresa>> getAllEmpresas(){
        /***
        try{

            List<Empresa> empresas = new ArrayList<Empresa>();
            empresaRepository.findAll().forEach(empresas::add);
            if (empresas.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(empresas, HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
         ***/
        List<Empresa> empresas = empresaService.getEmpresas();
        return ResponseEntity.ok(empresas);
    }

    @GetMapping("/contribuyentesrest/{pag}")
    public ResponseEntity<List<Contribuyente>> getAllContribuyentes(@PathVariable("pag") int pag){
        //List<Contribuyente> contribuyentes = contribuyenteService.getContribuyentes(); //antiguo

        if(pag >= 0 ) {
            List<Contribuyente> contribuyentes = contribuyenteService.getContribuyentesWeb(pag);
            return ResponseEntity.ok(contribuyentes);
        }
        return ResponseEntity.ok(null);
    }

    @GetMapping({"/contribuyentesrestfbn/{nombre}", "/contribuyentesrestfbn/{nombre}/", "/contribuyentesrestfbn/{nombre}/{pag}"})
    public ResponseEntity<List<Contribuyente>> getByNombreContribuyentes(
            @PathVariable("nombre") String nombre,
            @PathVariable(name="pag", required = false) Integer pag
    ){
        int hoja = 0;
        if( pag != null && pag > 0) hoja = pag;

        if(nombre != null) {
            List<Contribuyente> contribuyentes=null;
            if( !validaRutUtil.validaRut(nombre) ){
                contribuyentes = contribuyenteService.getContribuyentesByRazonSocial(nombre, hoja);
            }else{
                contribuyentes = contribuyenteService.getContribuyentesByRut(nombre);
            }

            return ResponseEntity.ok(contribuyentes);
        }
        return ResponseEntity.ok(null);
    }

    @GetMapping("/documentos/{id_documento}")
    public ResponseEntity<?> getDocumento(@PathVariable("id_documento") Long id_documento){
        if( id_documento!=null ){
            Documentos elDoc = documentosService.getDocumentoId(id_documento);
            return ResponseEntity.ok(elDoc);
        }else{
            System.out.println("ERRRO ");

           // return (ResponseEntity<Documentos>) ResponseEntity.noContent();
           // return ResponseEntity.ok(null);
           // return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/referencias")
    public ResponseEntity<?> setReferencias(){

        DocumentoReferencias laReferencia = new DocumentoReferencias(
               null,
                549985L,
                "2023-09-06",
                " referencia ESCUELA DE PEDAGOGIA",
                "801",
                "177268",
                "1"
        );

        documentoReferenciasService.setDocumentoReferencias(laReferencia);
        return ResponseEntity.ok("echo");

    }


    /*******
     * ejemplo *
    @PostMapping("/products")
    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDto productRecordDto ){
        var productModel = new ProductModel();
        BeanUtils.copyProperties(productRecordDto, productModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(productModel));

    }
    ******/

}
