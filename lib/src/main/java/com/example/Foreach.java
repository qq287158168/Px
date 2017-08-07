package com.example;

import java.io.File;
import java.io.FileFilter;

/**
 * Created by Admin on 2017/2/7.
 */

public class Foreach {
    public static void main(String[] args) {

//        List<Integer> a = new ArrayList<>();
//        a.add(1);
//        a.add(1);
//        a.add(1);
//        for (Iterator<Integer> it = a.iterator(); it.hasNext(); ) {
//            Integer ii = it.next();
//            System.out.println(ii);
//            //Â§ÑÁêÜ s
//        }
        String path = "C:\\Users\\Admin\\Desktop\\ª˙‘ÿappÕº∆¨";

        byte[] p = path.getBytes();

        String p1 = null;

        p1 = new String(p);

        File file = new File(p1);

        File[] a = file.listFiles(
                new FileFilter() {
                    @Override
                    public boolean accept(File file) {


                        String ss = "";
                        return true;
                    }

                }
        );

        for (int i = 0; i < a.length; i++) {

            System.out.println(a[i].getName());
        }


    }


}
