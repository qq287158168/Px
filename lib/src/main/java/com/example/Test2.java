package com.example;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

public class Test2 {


//    public static String houZhui1 = "@1.5x";
//    public static String houZhui2 = "@2x";
//    public static String houZhui3 = "@3x";
//    public static String dir = "f:\\aa";
    public static String dir1 = "f:\\a1";
//    public static String dir2 = "f:\\a2";
//    public static String dir3 = "f:\\a3";
//    public static String name1 = "";
//    public static String name2 = "xxf";

    public static void main(String[] args) {
        try {
            getFiles(dir1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * ͨ���ݹ�õ�ĳһ·�������е�Ŀ¼�����ļ�
     */
    public static void getFiles(String filePath) throws IOException {
        File root = new File(filePath);
        File[] files = root.listFiles();
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            String fileName = file.getName();
            if (fileName.contains("����")) {
                file.renameTo(new File(dir1 + "\\xxf_xiala" + ".png"));
            } else if (fileName.contains("�ͷ�")) {
                file.renameTo(new File(dir1 + "\\xxf_kfdh" + ".png"));
            } else if (fileName.contains("С����")) {
                file.renameTo(new File(dir1 + "\\xxf_xkt" + ".png"));
            } else if (fileName.contains("�޶���")) {
                file.renameTo(new File(dir1 + "\\xxf_wdd" + ".png"));
            } else if (fileName.contains("��У")) {
                file.renameTo(new File(dir1 + "\\xxf_hxjs" + ".png"));
            } else if (fileName.contains("����")) {
                file.renameTo(new File(dir1 + "\\xxf_fanhui" + ".png"));
            } else if (fileName.contains("�˿�")) {
                file.renameTo(new File(dir1 + "\\xxf_gkdh" + ".png"));
            } else if (fileName.contains("���п�")) {
                file.renameTo(new File(dir1 + "\\xxf_fxk" + ".png"));
            }
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
