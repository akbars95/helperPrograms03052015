package com.mtsmda.tools.gui.util;

/**
 * Created by c-DMITMINZ on 24.07.2015.
 */
public class StringUtil {
    public static boolean stringIsNotNULLOrEmpty(String string) {
        if (string != null && !string.trim().isEmpty()) {
            return true;
        }
        return false;
    }
}
