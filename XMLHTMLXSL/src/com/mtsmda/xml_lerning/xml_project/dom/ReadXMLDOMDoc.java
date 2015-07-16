package com.mtsmda.xml_lerning.xml_project.dom;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ReadXMLDOMDoc {
	
	public ReadXMLDOMDoc(String filename) {
		try{
			File fileXML = new File(filename);
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(fileXML);
			
			document.getDocumentElement().normalize();
			
			System.out.println("Root element - " + document.getDocumentElement().getNodeName());
			
			NodeList nodeList = document.getElementsByTagName("person");
			
			for(int i = 0; i < nodeList.getLength(); i++){
				Node node = nodeList.item(i);
				
				System.out.println("Current element - " + node.getNodeName());
				
				if(node.getNodeType() == Node.ELEMENT_NODE){
					Element element = (Element) node;
					
					System.out.println("Attribute id = " + element.getAttribute("id"));
					System.out.println("Element firstname = " + element.getElementsByTagName("firstname").item(0).getTextContent());
					System.out.println("Element lasttname = " + element.getElementsByTagName("lastname").item(0).getTextContent());
					System.out.println("Element nickname = " + element.getElementsByTagName("nickname").item(0).getTextContent());
					System.out.println("Element dateOfBirth = " + element.getElementsByTagName("dateOfBirth").item(0).getTextContent());
				}
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
}