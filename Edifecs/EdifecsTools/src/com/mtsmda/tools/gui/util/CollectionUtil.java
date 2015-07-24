package com.mtsmda.tools.gui.util;

import java.util.List;

/**
 * Created by c-DMITMINZ on 24.07.2015.
 */
public class CollectionUtil {

    public static boolean isListNotNullAndIsNotEmpty(List<?> list) {
        if (list != null && !list.isEmpty()) {
            return true;
        }
        return false;
    }

}