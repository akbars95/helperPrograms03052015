package com.mtsmda.tools.gui.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by c-DMITMINZ on 24.07.2015.
 */
public class FileDirectoryUtil {

    public static void renameFile(List<File> fileList, String what, String forWhat) {
        if (StringUtil.stringIsNotNULLOrEmpty(what) && StringUtil.stringIsNotNULLOrEmpty(forWhat) && CollectionUtil.isListNotNullAndIsNotEmpty(fileList)) {
            for (File currentFile : fileList) {
                String replaced = currentFile.getAbsolutePath().replace(what, forWhat);
                File newFile = new File(replaced);
                currentFile.renameTo(newFile);
            }
        }
    }

    public static List<File> getFileFromDirectoryOnPattern(File parentDirectory, String fileNamePattern) {
        List<File> fileList = new ArrayList<>();
        if (FileUtil.isDirectoryExists(parentDirectory)) {
            for (File current : parentDirectory.listFiles()) {
                if (FileUtil.isFileExists(current)) {
                    if (current.getAbsolutePath().contains(fileNamePattern)) {
                        fileList.add(current);
                    }
                }
            }
        }
        return fileList;
    }

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

}