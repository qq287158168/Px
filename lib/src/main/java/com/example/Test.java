package com.example;

import java.io.File;
import java.io.FileFilter;

public class Test {
    static String name = "2x";
    static String houZhui = "@2x";

    public static String dir = "f:\\aa\\"+ name;

    public static void main(String[] args) {
        getFiles(dir);
    }


    /*
     * ͨ���ݹ�õ�ĳһ·�������е�Ŀ¼�����ļ�
     */
    public static void getFiles(String filePath) {
        File root = new File(filePath);
        File[] files = root.listFiles();
        for (File file : files) {
            String fileName = file.getName();
            String all =  fileName.replaceAll(houZhui, "").replaceAll("-", "_");
            all = all.toLowerCase();
            boolean b = file.renameTo(new File(dir + "\\" + all));
        }
    }

    /**
     * �Զ������
     *
     * @author admin
     */
    static FileFilter filter = new FileFilter() {
        @Override
        public boolean accept(File pathname) {
            String filename = pathname.getName().toLowerCase();
            if (filename.contains(".xml")) {
                return true;
            } else {
                return false;
            }
        }
    };

}
