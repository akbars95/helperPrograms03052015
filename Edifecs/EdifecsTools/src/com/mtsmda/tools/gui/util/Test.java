package com.mtsmda.tools.gui.util;

import java.io.File;
import java.util.List;

/**
 * Created by c-DMITMINZ on 24.07.2015.
 */
public class Test {

    public static void main(String[] args) {

        //FileDirectoryUtil
        List<File> files = FileDirectoryUtil.getFileFromDirectoryOnPattern(new File("c:\\PROJECTS\\IntegrationFramework\\src\\test\\resources\\integrationTestData\\CA_RX\\CA_RX_2.2\\05AcknowledgementSupport\\"), "CA_RX22_Ak02_5_TrRej_EnRej");
        System.out.println(files.size());
        FileDirectoryUtil.renameFile(files, "CA_RX22_Ak02_5_TrRej_EnRej", "CA_RX22_Ak02_5_TrAc_EnDen");
        System.out.println();

        FileDirectoryUtil.deleteFile(new File("C:\\Edifecs\\XEServer\\profiles\\TM_Graph_DB\\log"));
        System.out.println("s");
    }

}