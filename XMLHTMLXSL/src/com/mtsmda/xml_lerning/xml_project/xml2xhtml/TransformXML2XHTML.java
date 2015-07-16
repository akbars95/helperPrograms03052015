package com.mtsmda.xml_lerning.xml_project.xml2xhtml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class TransformXML2XHTML {
	
	public void transformXML2HTML()
	{
		String xmlInput = "C:/benim/workspace/xml_project/personsMulti.xml";
		String xslInput = "C:/benim/workspace/xml_project/personsMulti.xsl";

		String xhtmlOutput = "C:/benim/workspace/xml_project/personsMulti.html";

		try
		{
			FileOutputStream xhtmlFileOutputStream = new FileOutputStream(xhtmlOutput);
			FileInputStream xmlFileInputStream = new FileInputStream(xmlInput);
			FileInputStream xslFileInputStream = new FileInputStream(xslInput);

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			StreamSource styleSource = new StreamSource(xslFileInputStream);
			Transformer transformer = transformerFactory.newTransformer(styleSource);

			StreamSource xmlSource = new StreamSource(xmlFileInputStream);
			StreamResult result = new StreamResult(xhtmlFileOutputStream);

			transformer.transform(xmlSource, result);
			System.out.println("Success!");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public ReturnResult transformXML2HTML(File fileXML, File fileXSL)
	{
		ReturnResult resultReturnResult = new ReturnResult();
		String nameXHTML = fileXML.getName();
		if(nameXHTML.endsWith("xml")){
			nameXHTML = nameXHTML.substring(0, nameXHTML.length() - 3);
			nameXHTML += "html";
		}
		String xhtmlOutput = new String(fileXML.getParent() + File.separator + nameXHTML);
		
		try
		{
			FileOutputStream xhtmlFileOutputStream = new FileOutputStream(xhtmlOutput);
			FileInputStream xmlFileInputStream = new FileInputStream(fileXML);
			FileInputStream xslFileInputStream = new FileInputStream(fileXSL);

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			StreamSource styleSource = new StreamSource(xslFileInputStream);
			Transformer transformer = transformerFactory.newTransformer(styleSource);

			StreamSource xmlSource = new StreamSource(xmlFileInputStream);
			StreamResult result = new StreamResult(xhtmlFileOutputStream);

			transformer.transform(xmlSource, result);
			resultReturnResult.setSuccess(1);
			resultReturnResult.setFilename(xhtmlOutput);
		}
		catch (Exception e)
		{
			resultReturnResult.setSuccess(0);
			resultReturnResult.setFilename(null);
		}
		
		return resultReturnResult;
	}
	
}