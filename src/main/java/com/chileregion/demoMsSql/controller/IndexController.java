package com.chileregion.demoMsSql.controller;

import com.chileregion.demoMsSql.domain.*;
import com.chileregion.demoMsSql.persistance.entities.ContribuyenteEntity;
import com.chileregion.demoMsSql.services.*;
import com.chileregion.demoMsSql.utils.Generar;
import com.chileregion.demoMsSql.utils.ProcesarImpUnico;
import com.chileregion.demoMsSql.utils.ValidaRutUtil;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
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
    private ContratoVacacionesService contratoVacacionesService;
    @Autowired
    private ContratoPersonalService contratoPersonalService;

    @Autowired
    private FeriadosService feriadosService;
    @Autowired
    @Lazy
    private ValidaRutUtil validaRutUtil;
    @Autowired
    @Lazy
    private ProcesarImpUnico procesarImpUnico;
    @Autowired
    @Lazy
    private Generar generar;

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
    @GetMapping("/contratoPersonal/{id_empresa}")
    public ResponseEntity<List<?>> getContribuyenteContrato( @PathVariable("id_empresa") Long id_empresa ){
        List<ContratoPersonal> contratoPersonal = contratoPersonalService.getContratosEmpresas(id_empresa);
        //System.out.println("contratoPersonal ");
       // System.out.println(id_empresa);
        //System.out.println(contratoPersonal);

        return ResponseEntity.ok(contratoPersonal);
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

    @GetMapping("/documentos_folio/{folio}/{id_empresa}")
    public ResponseEntity<?> getDocumentoFolio(
            @PathVariable("folio") Long folio,
            @PathVariable("id_empresa") Long id_empresa
    ){
        if(id_empresa == null)id_empresa=1L;

        if( folio!=null ){
            Documentos elDoc = documentosService.getDocumentoFolio(folio, id_empresa);
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
       if(false)documentoReferenciasService.setDocumentoReferencias(laReferencia);
        return ResponseEntity.ok(laReferencia);
    }

    @GetMapping("/ver_vacaciones/{id_contrato}")
    public ResponseEntity<List<?>> verVacaciones(@PathVariable("id_contrato") Long id_contrato){

        if(id_contrato == null){
            return ResponseEntity.ok(null);
        }

        /****
        ContratoVacaciones unaVacacion = new ContratoVacaciones(
          null,
          7L,
          "032023",
          "2023-01-02",
           "2023-01-20",
                23
        );
        //List<ContratoVacaciones> laVacacion =  contratoVacacionesService.getContratoVacaciones(unaVacacion.getIdContratoPersonal());
         *****/
        List<ContratoVacaciones> laVacacion =  contratoVacacionesService.getContratoVacaciones(id_contrato);
        return ResponseEntity.ok(laVacacion);
    }

    @GetMapping("/set_vacaciones")
    public ResponseEntity<?> agregarVacaciones(){
        ContratoVacaciones unaVacacion = new ContratoVacaciones(
                null,
                488L,
                "2023",
                "2023-01-02",
                "2023-01-20",
                23
        );
        if(!true)contratoVacacionesService.setContratoVacaciones(unaVacacion);
        return ResponseEntity.ok(unaVacacion);
    }

    @GetMapping("/calcularDias/{desde}/{hasta}")
    public ResponseEntity<?> calcularDias(
            @PathVariable(name="desde", required = false) String desde,
            @PathVariable(name="hasta", required = false) String hasta
    ){

        Integer respDias = generar.calcularDias(desde, hasta);
        String[] fechas = new String[]{desde, hasta, String.valueOf(respDias)};

        return ResponseEntity.ok(fechas);
    }

    @GetMapping("/feriados")
    public ResponseEntity<?> feriados(){
        List<Feriados> feriados = feriadosService.getFeriados("09-10-2023", "16-10-2023");
        //System.out.println(feriados);
        return ResponseEntity.ok(feriados);
    }

    @GetMapping("/jsoup/{mes}")
    public ResponseEntity<?> jSoup(@PathVariable("mes") int mes){
        try{
            String url = "https://www.sii.cl/valores_y_fechas/impuesto_2da_categoria/impuesto2023.htm";

            if(false){
                /**** Connection.Response, no solo permite acceso a la pagina ,
                 *  tambien a los recursos como imagenes, sonidos, etc... ****/

                Connection.Response res = Jsoup.connect(url)
                        .ignoreContentType(true)
                        .method(Connection.Method.GET)
                        .execute();
                return ResponseEntity.ok(res.body());
            }

            List<ImpUnicoSegCat> impUnicoSegCatList = new ArrayList<>();

            Document document = Jsoup.connect(url).get();
            //return ResponseEntity.ok(document.body().toString());
            //System.out.println("mes: "+ mes );
            //System.out.println("mes: "+ generar.mesesLlamada(mes-1) );

            //Element datos0 = document.getElementById("mes_septiembre");
            Element datos0 = document.getElementById(generar.mesesLlamada(mes-1));
            Elements elements = datos0.getElementsByTag("tbody");
            System.out.println(datos0.child(0).text());
            /****/
            for(Element elElement: elements ){
                Elements trs = elElement.getElementsByTag("tr");
              //  System.out.println("trs");
                //System.out.println(trs);
                for(Element elTr: trs){
                    Elements tds = elTr.getElementsByTag("td");
                   // System.out.println("");
                   // System.out.println(tds.first() );
                  //  System.out.println(tds.first().children() );
                  //  System.out.println( "|"+tds.first().children().text()+"|" );
                    if(tds.first().children().text().equals("MENSUAL")){
                        System.out.println("---");
                        System.out.println(tds.first().children().text());
                        /****
                        System.out.println(trs.get(0));
                        System.out.println(trs.get(1));
                        ******/
                        for(int row=0;row<8;row++){
                            Elements hijos = trs.get(row).children();
                            /****
                            System.out.println(hijos.get(1).text());
                            System.out.println(hijos.get(2).text());
                            System.out.println(hijos.get(3).text());
                            System.out.println(hijos.get(4).text());
                            System.out.println(hijos.get(5).text());
                            System.out.println("tds...");
                            ****/

                            impUnicoSegCatList.add(procesarImpUnico.make(hijos));
                        }
                        // System.out.println(tds.outerHtml());
                        //System.out.println(tds.textNodes());
                    }
                }
            }
            /****/

            //System.out.println("");
            //System.out.println("impUnicoSegCatList");
            //System.out.println(impUnicoSegCatList);

            return ResponseEntity.ok(impUnicoSegCatList);

            //String datos = String.valueOf(document.getElementById("mes_octubre"));
            //return ResponseEntity.ok(datos);
        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.notFound().build();
        }
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
