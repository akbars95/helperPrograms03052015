package com.mtsmda.xml_lerning.xml_project.xml2xhtml;

import java.io.File;

public class Run {

    public static void main(String[] args) {
        TransformXML2XHTML transformXML2XHTML = new TransformXML2XHTML();
        File fileXML = new File("E:\\simple\\helperPrograms03052015\\XMLHTMLXSL\\personsMulti.xml");
        File fileXSL = new File("E:\\simple\\helperPrograms03052015\\XMLHTMLXSL\\personsMulti.xsl");
        if (fileXML.exists() && fileXSL.exists()) {
            ReturnResult transformXML2HTML = transformXML2XHTML.transformXML2HTML(
                    fileXML,
                    fileXSL);
            if (transformXML2HTML.getSuccess() == 1
                    && transformXML2HTML.getFilename() != null) {
                System.out.println("Success!");
            } else {
                System.out.println("Error!");
            }
        }
    }

}