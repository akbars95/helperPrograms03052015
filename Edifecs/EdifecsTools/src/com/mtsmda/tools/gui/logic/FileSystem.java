package com.mtsmda.tools.gui.logic;

import java.io.File;

/**
 * Created by c-DMITMINZ on 17.07.2015.
 */
public class FileSystem {

    public static void deleteFile(String path) {
        if (path != null && !path.trim().isEmpty()) {
            deleteFile(new File(path));
        }
    }

    public static void deleteFile(File file) {
        if (file.isDirectory()) {
            if (file.list().length == 0) {
                file.delete();
                System.out.println("Directory is deleted : "
                        + file.getAbsolutePath());
            } else {
                String files[] = file.list();
                for (String temp : files) {
                    File fileDelete = new File(file, temp);
                    deleteFile(fileDelete);
                }
                if (file.list().length == 0) {
                    file.delete();
                    System.out.println("Directory is deleted : "
                            + file.getAbsolutePath());
                }
            }

        } else {
            file.delete();
            System.out.println("File is deleted : " + file.getAbsolutePath());
        }

    }

    public static void main(String[] args) {
        deleteFile(new File("C:\\Edifecs\\XEServer\\profiles\\TM_Graph_DB\\log"));
        System.out.println("s");
    }

}