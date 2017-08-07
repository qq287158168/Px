package com.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Admin on 2017/2/7.
 */

public class Io {
    public static void main(String[] args) throws IOException {
        String path = "C:\\Users\\Admin\\Desktop\\»úÔØappÍ¼Æ¬\\a.txt";
        File file = new File(path);
        FileInputStream fis = new FileInputStream(file);

        int b;

//        while ((b = fis.read()) != -1) {
//
//            byte b1 = (byte) b;
//            System.out.println(b + "");
//            System.out.println(b1 + "------");
//        }

        byte[] bytes = new byte[10];

        while ((b = fis.read(bytes)) != -1) {

            if (b == 10) {
                String s = new String(bytes, "GBK");
                System.out.println(s);
            } else if (b < 10) {
                byte[] bytes1 = new byte[b];
                for (int i = 0; i < b; i++) {
                    bytes1[i] = bytes[i];
                }
                String s = new String(bytes1, "GBK");
                System.out.println(s);
            }

        }


        fis.close();


    }
}
