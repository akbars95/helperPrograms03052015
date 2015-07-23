package com.mtsmda.tools.gui.logic;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

/**
 * Created by c-DMITMINZ on 23.07.2015.
 */
public class DomParserEncounterValidationResponse {

    private static Document createDocument(String fileName)throws Exception{
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(fileName);
        return document;
    }

    public static boolean modifyEncounterType(String fileName)throws Exception{
        Document document = createDocument(fileName);
        if(document == null){
            return false;
        }
        Node nodeEncounterValidationResponse = document.getFirstChild();
        Node nodeEncounterType = document.getElementsByTagName("tns:EncounterType").item(0);
        if(nodeEncounterType == null){
            return false;
        }
        nodeEncounterValidationResponse.removeChild(nodeEncounterType);

        return saveXMLFile(document, fileName);

    }

    private static boolean saveXMLFile(Document document, String fileName) throws Exception{
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File(fileName));
        transformer.transform(source, result);
        return true;
    }

}