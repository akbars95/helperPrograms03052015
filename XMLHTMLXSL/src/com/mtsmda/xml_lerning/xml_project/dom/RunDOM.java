package com.mtsmda.xml_lerning.xml_project.dom;

public class RunDOM {
	
	public static void main(String[] args) {
		/*try{
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			
			Document document = documentBuilder.newDocument();
			Element rootElement = document.createElement("persons");
			document.appendChild(rootElement);
			
			Element person = document.createElement("person");
			rootElement.appendChild(person);
			
			Attr attrID = document.createAttribute("id");
			attrID.setValue("1");
			person.setAttributeNode(attrID);
			
			Element firstNameElement = document.createElement("firstname");
			firstNameElement.appendChild(document.createTextNode("Mynzat"));
			person.appendChild(firstNameElement);
			
			Element lastNameElement = document.createElement("lastname");
			lastNameElement.appendChild(document.createTextNode("Dmitrii"));
			person.appendChild(lastNameElement);
			
			Element nickNameElement = document.createElement("nickname");
			nickNameElement.appendChild(document.createTextNode("MTSMDA"));
			person.appendChild(nickNameElement);
			
			Element dateOfBirthElement = document.createElement("dateOfBirth");
			dateOfBirthElement.appendChild(document.createTextNode("26.11.1990"));
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(new File("persons.xml"));
			transformer.transform(domSource, streamResult);
			
			System.out.println("Success!");
			
		}
		catch(Exception e){
			e.printStackTrace();
		}*/
		
		CreateXMLDOMDoc createXMLDOMDoc = new CreateXMLDOMDoc();
		createXMLDOMDoc.createPerson("1", "Mynzat", "Dmitrii", "MTSMDA", "23.12.1990");
		createXMLDOMDoc.createPerson("2", "Andreev", "Andrei", "Andrei", "20.02.1933");
		createXMLDOMDoc.createPerson("3", "Pavlov", "Pavel", "Pavlusha", "18.03.1992");
		createXMLDOMDoc.createPerson("4", "Artemov", "Artem", "Artemcik", "11.01.1982");
		createXMLDOMDoc.createPerson("5", "Evgeniev", "Evgenii", "Evgusha", "21.10.1989");
		
		createXMLDOMDoc.saveXMLDoc("personsMulti.xml");
		
		ReadXMLDOMDoc readXMLDOMDoc = new ReadXMLDOMDoc("C:/benim/workspace/xml_project/personsMulti.xml");
	}
	
}