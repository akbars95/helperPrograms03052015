package com.mtsmda.tools.gui.util;

import java.io.File;

/**
 * Created by c-DMITMINZ on 24.07.2015.
 */
public class FileUtil {
    public static boolean isFileExists(File file) {
        if (file.exists() && file.isFile()) {
            return true;
        }
        return false;
    }

    public static boolean isDirectoryExists(File file) {
        if (file.exists() && file.isDirectory()) {
            return true;
        }
        return false;
    }
}