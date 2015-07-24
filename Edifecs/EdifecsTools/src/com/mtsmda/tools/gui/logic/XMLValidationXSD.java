package com.mtsmda.tools.gui.logic;

import com.mtsmda.tools.gui.util.CollectionUtil;
import com.mtsmda.tools.gui.util.FileUtil;
import com.mtsmda.tools.gui.util.StringUtil;
import jdk.internal.org.xml.sax.ErrorHandler;
import jdk.internal.org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by c-DMITMINZ on 23.07.2015.
 */
public class XMLValidationXSD {

    public static void main(String[] args) throws Exception {
/*//        String parent = "C:\\PROJECTS\\IntegrationFramework\\src\\test\\resources\\integrationTestData\\CA_RX\\CA_RX_2.2\\";
        String parent = "c:\\PROJECTS\\IntegrationFramework\\src\\test\\resources\\integrationTestData\\CA_RX\\CA_RX_4.2\\";
        String xsd = "C:\\Edifecs\\ArtifactsRepository\\Guidelines\\EdctResponse.xsd";
        List<String> paths = new ArrayList<>();
        System.out.println(findFilesRecursively(parent, "resp_ca_cl", paths));
        System.out.println(paths.size());
        for (String path : paths) {
            System.out.println(path);
//            DomParserEncounterValidationResponse.modifyEncounterType(path);
            List<SAXParseException> saxParseExceptions = validateXMLSchema(xsd, path);
            for (SAXParseException saxParseException : saxParseExceptions) {
                System.out.println(saxParseException.getMessage());
            }

        }*/


    }

    private static String findFilesRecursively(String fileName, String fileNamePattern, List<String> paths) {
        if (fileName != null && !fileName.trim().isEmpty()) {
            return findFilesRecursively(new File(fileName), fileNamePattern, paths);
        }
        return "";
    }

    private static String findFilesRecursively(File fileDirectory, String fileNamePattern, List<String> paths) {
        if (fileDirectory.exists()) {
            if (fileDirectory.isDirectory()) {
                if (fileDirectory.listFiles().length != 0) {
                    for (File currentFile : fileDirectory.listFiles())
                        findFilesRecursively(currentFile, fileNamePattern, paths);
                }
            } else {
                if (fileDirectory.getAbsolutePath().contains(fileNamePattern) && fileDirectory.getAbsolutePath().endsWith(".xml")) {
                    paths.add(fileDirectory.getAbsolutePath());
                }
            }
        }
        return "";
    }


    public static List<SAXParseException> validateXMLSchema(String xsdPath, String xmlPath) {
        final List<SAXParseException> exceptions = new LinkedList<SAXParseException>();
        try {
            SchemaFactory factory =
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));
             /*validator.setErrorHandler(new org.xml.sax.ErrorHandler() {
                @Override
                public void warning(org.xml.sax.SAXParseException exception) throws org.xml.sax.SAXException {
                    exceptions.add(exception);
                }

                @Override
                public void error(org.xml.sax.SAXParseException exception) throws org.xml.sax.SAXException {
                    exceptions.add(exception);
                }

                @Override
                public void fatalError(org.xml.sax.SAXParseException exception) throws org.xml.sax.SAXException {
                    exceptions.add(exception);
                }
            });*/
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return exceptions;
    }









}