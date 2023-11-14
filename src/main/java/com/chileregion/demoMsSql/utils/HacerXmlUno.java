package com.chileregion.demoMsSql.utils;

import java.io.File;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.StringWriter;

public class HacerXmlUno {

    // Objecto que representa al documento XML
    Document documento;

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

    public void crearCaratula(Element element){
        Element rutEmisor = documento.createElement("RutEmisor");
        rutEmisor.setTextContent("99586050-3");

        Element rutEnvia = documento.createElement("RutEnvia");
        rutEnvia.setTextContent("16388980-3");

        Element rutReceptor = documento.createElement("RutReceptor");
        rutReceptor.setTextContent("60803000-K");

        Element fchResol = documento.createElement("FchResol");
        fchResol.setTextContent("2014-08-22");

        Element nroResol = documento.createElement("NroResol");
        nroResol.setTextContent("80");

        Element tmstFirmaEnv = documento.createElement("TmstFirmaEnv");
        tmstFirmaEnv.setTextContent("2023-05-22T16:15:33");

        Element subTotDTE = documento.createElement("SubTotDTE");

        Element tpoDTE = documento.createElement("TpoDTE");
        tpoDTE.setTextContent("33");

        Element nroDTE = documento.createElement("NroDTE");
        nroDTE.setTextContent("1");

        element.appendChild(rutEmisor);
        element.appendChild(rutEnvia);
        element.appendChild(rutReceptor);
        element.appendChild(fchResol);
        element.appendChild(nroResol);
        element.appendChild(tmstFirmaEnv);
        subTotDTE.appendChild(tpoDTE);
        subTotDTE.appendChild(nroDTE);
        element.appendChild(subTotDTE);
    }

    public void crearDte(Element element){
        Element dte = documento.createElement("DTE");
        dte.setAttribute("version", "1.0");
        crearDocDte(dte);
        element.appendChild(dte);
    }

    public void crearDocDte(Element element){
        Element documentoDte = documento.createElement("Documento");
        documentoDte.setAttribute("ID", "T33F202");
        encabezadoDocDte(documentoDte);
        element.appendChild(documentoDte);
    }

    public void encabezadoDocDte(Element element){
        Element documentoDte = documento.createElement("Encabezado");

        Element idDoc = documento.createElement("IdDoc");
        String[] idDoscs = {"TipoDTE", "Folio", "FchEmis", "FchVenc"};
        for (String item : idDoscs) {
            Element item_ = documento.createElement(item);
            idDoc.appendChild(item_);
        }

        Element emisor = documento.createElement("Emisor");
        String[] emisors = {"RUTEmisor", "RznSoc", "GiroEmis", "Acteco", "DirOrigen", "CmnaOrigen"};
        for (String item : emisors) {
            Element item_ = documento.createElement(item);
            emisor.appendChild(item_);
        }

        Element receptor = documento.createElement("Receptor");
        String[] receptors = {"RUTRecep", "RznSocRecep", "GiroRecep", "DirRecep", "CmnaRecep"};
        for (String item : receptors) {
            Element item_ = documento.createElement(item);
            receptor.appendChild(item_);
        }

        Element totales = documento.createElement("Totales");
        String[] totaless = {"MntNeto", "MntExe", "TasaIVA", "IVA", "MntTotal"};
        for (String item : totaless) {
            Element item_ = documento.createElement(item);
            totales.appendChild(item_);
        }


        documentoDte.appendChild(idDoc);
        documentoDte.appendChild(emisor);
        documentoDte.appendChild(receptor);
        documentoDte.appendChild(totales);

        element.appendChild(documentoDte);
    }
    public void crearDocumentoXml() {
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
        // Establecemos el contenido del titulo
        setDTE.setAttribute("ID", "SetDoc");
        entrada.appendChild(setDTE);

        Element caratula = documento.createElement("Caratula");
        caratula.setAttribute("version", "1.0");
        crearCaratula(caratula);
        setDTE.appendChild(caratula);

        crearDte(setDTE);

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
