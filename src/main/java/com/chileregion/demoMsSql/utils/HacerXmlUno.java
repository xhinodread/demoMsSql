package com.chileregion.demoMsSql.utils;

import java.io.File;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
// Clases para la creacion y manejo de XML
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import com.chileregion.demoMsSql.domain.CaratulaXml;
import com.chileregion.demoMsSql.domain.DetalleXml;
import com.chileregion.demoMsSql.domain.EncabezadoXml;
import com.chileregion.demoMsSql.domain.encabezadoXml.Emisor;
import com.chileregion.demoMsSql.domain.encabezadoXml.IdDoc;
import com.chileregion.demoMsSql.domain.encabezadoXml.Receptor;
import com.chileregion.demoMsSql.domain.encabezadoXml.Totales;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.StringWriter;

public class HacerXmlUno {

    // Objecto que representa al documento XML
    Document documento;

    private int cnt =0;

    /**
     * Creamos el documento XML
     *
     * @throws ParserConfigurationException
     */
    public HacerXmlUno() throws ParserConfigurationException {
        // Creamos los objectos de creacion de Documentos XML
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder constructor = docFactory.newDocumentBuilder();

        // Creamos el documento XML
        documento = constructor.newDocument();
    }

    /**
     * Regresa el documento “plano» sin saltos de linea
     *
     * @return Documento XML plano
     * @throws TransformerConfigurationException
     * @throws TransformerException
     */
    public String convertirString() throws TransformerConfigurationException, TransformerException {
        // Creamos el objecto transformador
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        // Creamos el escritor a cadena de texto
        StringWriter writer = new StringWriter();
        // Fuente de datos, en este caso el documento XML
        DOMSource source = new DOMSource(documento);
        // Resultado, el cual se almacenara en el objecto writer
        StreamResult result = new StreamResult(writer);
        // Efectuamos la transformacion a lo que indica el objecto resultado, writer apuntara a el resultado
        transformer.transform(source, result);
        // Convertimos el buffer de writer en cadena de texto
        String output = writer.getBuffer().toString();

        return output;
    }

    /**
     * Envia el documento XML a un archivo
     * @throws TransformerConfigurationException
     * @throws TransformerException
     */
    public void escribirArchivo() throws TransformerConfigurationException, TransformerException {
        // Creamos el objecto transformador
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        // Archivo donde almacenaremos el XML
        File archivo = new File("../xmls/documento.xml");

        // Fuente de datos, en este caso el documento XML
        DOMSource source = new DOMSource(documento);
        // Resultado, el cual almacena en el archivo indicado
        StreamResult result = new StreamResult(archivo);
        // Transformamos de ña fuente DOM a el resultado, lo que almacena todo en el archivo
        transformer.transform(source, result);
    }

    /**
     * Creamos un documento con un elemento principal y varios subElementos
     */

    private void crearCaratula(Element element, CaratulaXml caratulaXml){
        Element caratula = documento.createElement("Caratula");
        caratula.setAttribute("version", "1.0");

        Element rutEmisor = documento.createElement("RutEmisor"); rutEmisor.setTextContent(caratulaXml.getRutEmisor());
        Element rutEnvia = documento.createElement("RutEnvia"); rutEnvia.setTextContent(caratulaXml.getRutEnvia());
        Element rutReceptor = documento.createElement("RutReceptor"); rutReceptor.setTextContent("60803000-K");
        Element fchResol = documento.createElement("FchResol"); fchResol.setTextContent("2014-08-22");
        Element nroResol = documento.createElement("NroResol"); nroResol.setTextContent("80");

        LocalTime localTime1 = LocalTime.now();
        //Element tmstFirmaEnv = documento.createElement("TmstFirmaEnv"); tmstFirmaEnv.setTextContent("2023-05-22T16:15:33");
        Element tmstFirmaEnv = documento.createElement("TmstFirmaEnv"); tmstFirmaEnv.setTextContent("2023-05-22T"+localTime1.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        Element subTotDTE = documento.createElement("SubTotDTE");
        Element tpoDTE = documento.createElement("TpoDTE"); tpoDTE.setTextContent("33");
        Element nroDTE = documento.createElement("NroDTE"); nroDTE.setTextContent("1");

        caratula.appendChild(rutEmisor);
        caratula.appendChild(rutEnvia);
        caratula.appendChild(rutReceptor);
        caratula.appendChild(fchResol);
        caratula.appendChild(nroResol);
        caratula.appendChild(tmstFirmaEnv);
        subTotDTE.appendChild(tpoDTE);
        subTotDTE.appendChild(nroDTE);
        caratula.appendChild(subTotDTE);
        element.appendChild(caratula);
    }
    private void crearDte(Element element, EncabezadoXml encabezadoXml, List<DetalleXml> detalleXml){
        Element dte = documento.createElement("DTE");
        dte.setAttribute("version", "1.0");
        crearDocDte(dte, encabezadoXml, detalleXml);
        signatureDocDte(dte);
        element.appendChild(dte);
    }
    private void crearDocDte(Element element, EncabezadoXml encabezadoXml, List<DetalleXml> detalleXml){
        Element documentoDte = documento.createElement("Documento");
        String IdCodDte = "T33F"+encabezadoXml.getIdDoc().getFolio();
        documentoDte.setAttribute("ID", IdCodDte);
        encabezadoDocDte(documentoDte, encabezadoXml);
        detalleDocDte(documentoDte, detalleXml);
        referenciasDocDte(documentoDte);
        tedDocDte(documentoDte);
        tmstFirmaDocDte(documentoDte);
        element.appendChild(documentoDte);
    }
    private void encabezadoDocDte(Element element, EncabezadoXml encabezadoXml){
        Element documentoDte = documento.createElement("Encabezado");

        Element idDoc = documento.createElement("IdDoc");
        IdDoc idDoc1 = encabezadoXml.getIdDoc();

        String[] idDoscs = {"TipoDTE", "Folio", "FchEmis", "FchVenc"};
        String[] datosIdDoscs = {idDoc1.getTipoDTE(), idDoc1.getFolio(), idDoc1.getFchEmis(), idDoc1.getFchVenc()};
        int cnt=0;
        for (String item : idDoscs) {
            Element item_ = documento.createElement(item);
            item_.setTextContent(datosIdDoscs[cnt]);
            idDoc.appendChild(item_);
            cnt++;
        }

        cnt=0;
        Element emisor = documento.createElement("Emisor");
        Emisor emisor1 = encabezadoXml.getEmisor();
        String[] emisors = {"RUTEmisor", "RznSoc", "GiroEmis", "Acteco", "DirOrigen", "CmnaOrigen"};
        String[] datosEmisors = {emisor1.getRUTEmisor(), emisor1.getRznSoc(), emisor1.getGiroEmis(), emisor1.getActeco(), emisor1.getDirOrigen(), emisor1.getCmnaOrigen()};
        //System.out.println(datosEmisors);
        for (String item : emisors) {
            Element item_ = documento.createElement(item);
            //System.out.println(datosEmisors[cnt]);
            item_.setTextContent(datosEmisors[cnt]);
            emisor.appendChild(item_);
            cnt++;
        }

        cnt=0;
        Element receptor = documento.createElement("Receptor");
        Receptor receptor1 = encabezadoXml.getReceptor();
        String[] receptors = {"RUTRecep", "RznSocRecep", "GiroRecep", "DirRecep", "CmnaRecep"};
        String[] datosReceptors = {receptor1.getRUTRecep(), receptor1.getRznSocRecep() ,receptor1.getGiroRecep(), receptor1.getDirRecep(), receptor1.getCmnaRecep()};
        for (String item : receptors) {
            Element item_ = documento.createElement(item);
            item_.setTextContent(datosReceptors[cnt]);
            receptor.appendChild(item_);
            cnt++;
        }

        cnt=0;
        Element totales = documento.createElement("Totales");
        Totales totales1 = encabezadoXml.getTotales();
        String[] totaless = {"MntNeto", "MntExe", "TasaIVA", "IVA", "MntTotal"};
        String[] datosTotaless ={totales1.getMntNeto(), totales1.getMntExe(), totales1.getTasaIVA(), totales1.getIVA(), totales1.getMntTotal()};
        for (String item : totaless) {
            Element item_ = documento.createElement(item);
            item_.setTextContent(datosTotaless[cnt]);
            totales.appendChild(item_);
            cnt++;
        }
        documentoDte.appendChild(idDoc);
        documentoDte.appendChild(emisor);
        documentoDte.appendChild(receptor);
        documentoDte.appendChild(totales);
        element.appendChild(documentoDte);
    }
    private void detalleDocDte(Element element, List<DetalleXml> detalleXml){
        this.cnt=1;
        detalleXml.forEach(valor->{
            Element detalleDocDte = documento.createElement("Detalle");
            String[] itemDetalle = {"NroLinDet", "NmbItem", "QtyItem", "UnmdItem", "PrcItem", "MontoItem"};
            String[] valores = {String.valueOf(this.cnt), valor.getNmbItem(), valor.getQtyItem(), valor.getUnmdItem(), valor.getPrcItem(), valor.getMontoItem() };
            int contDet =0;
            for (String item : itemDetalle) {
                Element item_ = documento.createElement(item);
                item_.setTextContent(valores[contDet]);
                detalleDocDte.appendChild(item_);
                contDet++;
            }
            element.appendChild(detalleDocDte);
            this.cnt++;
        });
    }
    private void referenciasDocDte(Element element){
        Element referenciasDocDte = documento.createElement("Referencia");
        String[] itemDetalle = {"NroLinRef", "TpoDocRef", "FolioRef", "FchRef", "RazonRef"};
        for (String item : itemDetalle) {
            Element item_ = documento.createElement(item);
            referenciasDocDte.appendChild(item_);
        }
        element.appendChild(referenciasDocDte);
    }
    private void tedDocDte(Element element){
        Element tedDocDte = documento.createElement("TED");
        tedDocDte.setAttribute("version", "1.0");

        String[] itemDetalle = {"DD", "FRMT"};
        for (String item : itemDetalle) {
            Element item_ = documento.createElement(item);
            tedDocDte.appendChild(item_);
        }
        element.appendChild(tedDocDte);
    }
    private void tmstFirmaDocDte(Element element){
        Element tmstFirmaDocDte = documento.createElement("TmstFirma");
        element.appendChild(tmstFirmaDocDte);
    }
    private void signatureDocDte(Element element){
        Element signatureDocDte = documento.createElement("Signature");
        signatureDocDte.setAttribute("xmlns", "http://www.w3.org/2000/09/xmldsig#");

        element.appendChild(signatureDocDte);
    }
    public void crearDocumentoXml(CaratulaXml caratulaXml, EncabezadoXml encabezadoXml, List<DetalleXml> detalleXml) {

        //System.out.println(caratulaXml);
        //System.out.println(encabezadoXml.getIdDoc().getTipoDTE());

        // Creamos el elemento principal
        Element entrada = documento.createElement("EnvioDTE");
        // Hacemos el elemento entrada descender directo del nodo XML principal
        entrada.setAttribute("xmlns", "http://www.sii.cl/SiiDte");
        entrada.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        entrada.setAttribute("xsi:schemaLocation", "http://www.sii.cl/SiiDte EnvioDTE_v10.xsd");
        entrada.setAttribute("version", "1.0");
        documento.appendChild(entrada);

        // Creamos el Elemento de SetDTE ****************************************************
        Element setDTE = documento.createElement("SetDTE");
        setDTE.setAttribute("ID", "SetDoc");
        entrada.appendChild(setDTE);

        // Caratula
        crearCaratula(setDTE, caratulaXml);

        // DTE
        crearDte(setDTE, encabezadoXml, detalleXml);

        //Creamos mas elemento Signature ****************************************************
        Element signature = documento.createElement("Signature");
        signature.setAttribute("xmlns", "http://www.w3.org/2000/09/xmldsig#");
        signature.setTextContent("hashRaygoza");
        entrada.appendChild(signature);

     }

    public void crearDocumento() {
        // Creamos el elemento principal
        Element entrada = documento.createElement("EnvioDTE");
        // Hacemos el elemento entrada descender directo del nodo XML principal
        entrada.setAttribute("xmlns", "http://www.sii.cl/SiiDte");
        entrada.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        entrada.setAttribute("xsi:schemaLocation", "http://www.sii.cl/SiiDte EnvioDTE_v10.xsd");
        entrada.setAttribute("version", "1.0");
        documento.appendChild(entrada);

        // Creamos el Elemento de titulo
        Element titulo = documento.createElement("SetDTE");
        // Establecemos el contenido del titulo
        titulo.setTextContent("Creacion de XML");
        // Indicamos que el elemento titulo desciende de entrada
        entrada.appendChild(titulo);

        //Creamos mas elementos
        Element autor = documento.createElement("AUTOR");
        autor.setTextContent("hashRaygoza");
        entrada.appendChild(autor);

        //Elemento fecha
        Element fecha = documento.createElement("FECHA");
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendario = Calendar.getInstance();
        Date date = new Date(calendario.getTimeInMillis());

        fecha.setTextContent(formato.format(date));
        entrada.appendChild(fecha);
    }




}
