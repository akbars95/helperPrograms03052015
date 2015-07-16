package com.mtsmda.xml_lerning.xml_project.dom;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class CreateXMLDOMDoc {
	
	private DocumentBuilderFactory documentBuilderFactory;
	private DocumentBuilder documentBuilder;
	private TransformerFactory transformerFactory;
	private Transformer transformer;
	private DOMSource domSource;
	private StreamResult streamResult;
	private Document document;
	private Element rootElement;
	
	private void createRootElement(){
		try {
			document = documentBuilder.newDocument();
			rootElement = document.createElement("persons");
			document.appendChild(rootElement);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createPerson(String personId, String personFirstname, String personLastname, String personNickname, String personDateOfBirth){
		try {
			Element person = document.createElement("person");
			rootElement.appendChild(person);
			
			Attr attrID = document.createAttribute("id");
			attrID.setValue(personId);
			person.setAttributeNode(attrID);
			
			Element firstNameElement = document.createElement("firstname");
			firstNameElement.appendChild(document.createTextNode(personFirstname));
			person.appendChild(firstNameElement);
			
			Element lastNameElement = document.createElement("lastname");
			lastNameElement.appendChild(document.createTextNode(personLastname));
			person.appendChild(lastNameElement);
			
			Element nickNameElement = document.createElement("nickname");
			nickNameElement.appendChild(document.createTextNode(personNickname));
			person.appendChild(nickNameElement);
			
			Element dateOfBirthElement = document.createElement("dateOfBirth");
			dateOfBirthElement.appendChild(document.createTextNode(personDateOfBirth));
			person.appendChild(dateOfBirthElement);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void saveXMLDoc(String filename){
		try{
			transformerFactory = TransformerFactory.newInstance();
			transformer = transformerFactory.newTransformer();
			domSource = new DOMSource(document);
			streamResult = new StreamResult(new File(filename));
			transformer.transform(domSource, streamResult);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public CreateXMLDOMDoc() {
		try{
			documentBuilderFactory = DocumentBuilderFactory.newInstance();
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			
			createRootElement();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}