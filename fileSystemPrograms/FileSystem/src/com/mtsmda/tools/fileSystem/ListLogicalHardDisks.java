package com.mtsmda.tools.fileSystem;

import java.io.File;
import java.io.IOException;

/**
 * Created by c-DMITMINZ on 16.07.2015.
 */
public class ListLogicalHardDisks {

    public static void main(String[] args) {

        // ?????? ??????? ??????
        File root[] = File.listRoots();
        for(int i = 0; i < root.length; i++){
            File current = root[i];
            try {
                s("" + current.getCanonicalPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private static void s(Object o){
        System.out.println(o);
    }

}