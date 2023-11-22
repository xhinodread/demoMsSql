package com.chileregion.demoMsSql.utils.xml;

import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

import static com.chileregion.demoMsSql.utils.xml.XmlAtributos.AtributosEntrada.REFERENCE_URI;

/***
 * Este es un ejemplo del formato XML para el envió de Semilla firmada de acuerdo al
 * Estándar XML Digital Signature. Los demás nombres y etiquetas son Obligatorios.
 */
public class FirmarSemilla {
    private Document documento;
    private Element signature;
    private Element getToken;
    private Element item;
    private Element semilla;
    private Element signedInfo;
    private Element canonicalizationMethod;
    private Element signatureMethod;
    private Element reference;
    private Element transforms;
    private Element transform;
    private Element digestMethod;
    private Element digestValue;
    private Element signatureValue;
    private Element keyInfo;
    private Element keyValue;
    private Element rSAKeyValue;
    private Element modulus;
    private Element exponent;
    private final Element x509Data;
    private Element x509Certificate;
    private int cnt =0;
    public FirmarSemilla() throws ParserConfigurationException {
        // Creamos los objectos de creacion de Documentos XML
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder constructor = docFactory.newDocumentBuilder();
        // Creamos el documento XML
        documento = constructor.newDocument();
        this.signature = documento.createElement("Signature");
        this.getToken =documento.createElement("getToken");
        this.item =documento.createElement("item");
        this.semilla =documento.createElement("Semilla");
        this.signedInfo =documento.createElement("SignedInfo");
        this.canonicalizationMethod =documento.createElement("CanonicalizationMethod");
        this.signatureMethod =documento.createElement("SignatureMethod");
        this.signatureValue = documento.createElement("SignatureValue");
        this.reference =documento.createElement("Reference");
        this.transforms =documento.createElement("Transforms");
        this.transform =documento.createElement("Transform");
        this.digestMethod =documento.createElement("DigestMethod");
        this.digestValue =documento.createElement("DigestValue");
        this.keyInfo =documento.createElement("KeyInfo");
        this.keyValue =documento.createElement("KeyValue");
        this.rSAKeyValue = documento.createElement("RSAKeyValue");
        this.modulus =documento.createElement("Modulus");
        this.exponent =documento.createElement("Exponent");
        this.x509Data =documento.createElement("X509Data");
        this.x509Certificate =documento.createElement("X509Certificate");
    }
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
    public void crearXml(){
        XmlAtributosInSwitch xmlAtributosInSwitch= new XmlAtributosInSwitch();

        this.semilla.setTextContent("10"); // Valor de la semilla
        this.item.appendChild(this.semilla);
        this.getToken.appendChild(this.item);

        this.signature.setAttribute("xmlns", xmlAtributosInSwitch.enumEntradaInSwitch(XmlAtributos.AtributosEntrada.SIGNATURE_XMLS));

        this.canonicalizationMethod.setAttribute("Algorithm", xmlAtributosInSwitch.enumEntradaInSwitch(XmlAtributos.AtributosEntrada.CANON_ALGOR));
        this.signatureMethod.setAttribute("Algorithm", xmlAtributosInSwitch.enumEntradaInSwitch(XmlAtributos.AtributosEntrada.SIGNMETH_ALGOR));
        this.signedInfo.appendChild(this.canonicalizationMethod);
        this.signedInfo.appendChild(this.signatureMethod);

        this.transform.setAttribute("Algorithm", xmlAtributosInSwitch.enumEntradaInSwitch(XmlAtributos.AtributosEntrada.TRANSFORM_ALGOR));
        this.transforms.appendChild(this.transform);
        this.reference.setAttribute("URI", xmlAtributosInSwitch.enumEntradaInSwitch(XmlAtributos.AtributosEntrada.REFERENCE_URI));
        this.reference.appendChild(this.transforms);
        this.digestMethod.setAttribute("Algorithm", xmlAtributosInSwitch.enumEntradaInSwitch(XmlAtributos.AtributosEntrada.DIGMETH_ALGOR));
        this.reference.appendChild(this.digestMethod);
        this.digestValue.setTextContent(xmlAtributosInSwitch.enumValoresInSwitch(XmlAtributos.ValoresEntrada.DIGVAL));
        this.reference.appendChild(this.digestValue);

        this.signedInfo.appendChild(this.reference);
        this.signature.appendChild(this.signedInfo);
        this.signatureValue.setTextContent(xmlAtributosInSwitch.enumValoresInSwitch(XmlAtributos.ValoresEntrada.SIGNATURE_VALUE));
        this.signature.appendChild(this.signatureValue);

        this.modulus.setTextContent(xmlAtributosInSwitch.enumValoresInSwitch(XmlAtributos.ValoresEntrada.MODULULS));
        this.rSAKeyValue.appendChild(this.modulus);
        this.exponent.setTextContent(xmlAtributosInSwitch.enumValoresInSwitch(XmlAtributos.ValoresEntrada.EXPONENT));
        this.rSAKeyValue.appendChild(this.exponent);
        this.keyValue.appendChild(this.rSAKeyValue);
        this.keyInfo.appendChild(this.keyValue);

        this.x509Certificate.setTextContent(xmlAtributosInSwitch.enumValoresInSwitch(XmlAtributos.ValoresEntrada.X509CERT));
        this.x509Data.appendChild(this.x509Certificate);
        this.keyInfo.appendChild(this.x509Data);

        this.signature.appendChild(this.keyInfo);

        this.getToken.appendChild(this.signature);

        documento.appendChild(this.getToken);
    }
}
