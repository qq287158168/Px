package com.example.parse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PRM {

    private static BufferedReader BDTXR_reader;
    private static BufferedReader CCTXA_reader;
    private static int count;//记录bdtxr的数量
    private static int count2;//记录cctxa的数量
    private static int count_bijao;
    private static int bdhangshu;
    private static int cchangshu;
    private static String datex = "2017-07-20";
    private static String dateNow;

    public static void main(String[] args) throws IOException {


//        System.out.println("请输入16进制源码BF文件路径：");
//        String BDTXAR_File = new Scanner(System.in).nextLine();
//        System.out.println("请输入CCTXA的txt文件路径：");
//        String CCTXA_File = new Scanner(System.in).nextLine();
//
//        System.out.println("请输入要解析的北斗卡号：");
//        int baiheID = new Scanner(System.in).nextInt();
//
//        System.out.println("请输入要解析数据的日期：(如：2017-06-29)");
//        datex = new Scanner(System.in).nextLine();

        String BDTXAR_File = "f:\\BDLC\\20170720TCP.txt";
        String CCTXA_File = "f:\\BDLC\\全部数据2017-07-20 17-52-10.txt";
        int baiheID = 224418;
//        int zhihuijiID = 457390;


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            File fileTXR = new File(BDTXAR_File);
            BDTXR_reader = new BufferedReader(new FileReader(fileTXR));
            String s_TXR;
            while ((s_TXR = BDTXR_reader.readLine()) != null) {

                bdhangshu++;


                if (s_TXR.startsWith(datex)) {
                    s_TXR = s_TXR.replaceAll("\\s", ",");
                    String[] BFS = s_TXR.split(",");
                    dateNow = BFS[1];
                }

                if (s_TXR.startsWith(String.valueOf(baiheID))) {
                    s_TXR = s_TXR.replaceAll("\\s", ",");
                    String[] BFS = s_TXR.split(",");
                    String sBF = BFS[1];
                    going(sdf, sBF, "BDTXR");
                }


            }
            BDTXR_reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            File file_TXA = new File(CCTXA_File);
            CCTXA_reader = new BufferedReader(new FileReader(file_TXA));
            String s_TXA;
            while ((s_TXA = CCTXA_reader.readLine()) != null) {
                cchangshu++;
                if (s_TXA.startsWith("$CCTXA")) {
                    String[] BFS = s_TXA.split(",");
                    String sBF_3 = BFS[BFS.length - 1];
                    String sBF = sBF_3.substring(0, sBF_3.length() - 3);
                    going(sdf, sBF, "CCTXA");
                }
            }
            CCTXA_reader.close();
        } catch (Exception e) {
            CCTXA_reader.close();
            e.printStackTrace();
        }


        String str = "(2017/6/23 10:10:41) 24 42 44 54 58 52 2C 31 \n" +
                "(2017/6/23 10:10:41) 2C 30 31 38 38 31 33 34 \n" +
                "(2017/6/23 10:10:41) 2C 31 2C 2C 2A 34 33 0D \n" +
                "(2017/6/23 10:10:41) 0A ";
    }

    private static void going(SimpleDateFormat sdf, String bf, String tx) throws ParseException {
        count++;
        int bfLen = bf.length();
        if (bfLen != 150) {
            System.out.println("---数据异常:" + count);
            return;
        }

        Date date = sdf.parse("2016-01-01 00:00:00");
        long longDate = date.getTime();

        char[] charsBF = bf.toCharArray();
        int[] ii = new int[75];
        String[] hex = new String[75];
        for (int i = 0; i < 75; i++) {
            ii[i] = Integer.parseInt("" + charsBF[i * 2] + charsBF[i * 2 + 1], 16);
            hex[i] = "" + charsBF[i * 2] + charsBF[i * 2 + 1];
        }

        if (tx.equals("CCTXA")) {
            count2++;
            count = count2;
            System.out.println("---第" + count + "包" + tx + "数据,行数：" + cchangshu);
        } else {
            System.out.println("---第" + count + "包" + tx + "数据,接收时间是：" + dateNow + ",行数：" + bdhangshu);
        }


//        System.out.println(Arrays.toString(hex));

        int fxf = (ii[2] << 16) + (ii[3] << 8) + ii[4];


        long shijian = (ii[5] << 24) + (ii[6] << 16) + (ii[7] << 8) + ii[8];
        long lately_ms = shijian * 1000;
        long shicha8 = 8 * 3600 * 1000;
        long date_to = longDate + lately_ms + shicha8;
        String result = sdf.format(new Date(date_to));
        int jingdu = (ii[9] << 24) + (ii[10] << 16) + (ii[11] << 8) + ii[12];
        BigDecimal dian1 = BigDecimal.valueOf(0.000001);
        BigDecimal jingduzhi = BigDecimal.valueOf(jingdu);
        BigDecimal jd = dian1.multiply(jingduzhi).setScale(6, RoundingMode.HALF_UP);
        int weidu = (ii[13] << 24) + (ii[14] << 16) + (ii[15] << 8) + ii[16];
        BigDecimal weiduzhi = BigDecimal.valueOf(weidu);
        BigDecimal wd = dian1.multiply(weiduzhi).setScale(6, RoundingMode.HALF_UP);
        int gaocheng = (ii[17] << 8) + ii[18];
        int sudu = (ii[19] << 8) + ii[20];
        int fangxiang = (ii[21] << 8) + ii[22];
        System.out.println("时间 1：" + result + "，经度" + jd + "，纬度" + wd + ",高程：" + gaocheng
                + "米,速度：" + sudu + "节,航向：" + fangxiang);

        // TODO: 2017/6/23 第二包位置信息
        long date2 = date_to + ii[23] * 1000;
        String result2 = sdf.format(new Date(date2));
        double jdc2 = (ii[24] << 16) + (ii[25] << 8) + ii[26];
        BigDecimal jd2 = getBigDecimal(ii[24], dian1, jd, jdc2);
        int weiduc2 = (ii[27] << 16) + (ii[28] << 8) + ii[29];
        BigDecimal wd2 = getBigDecimal(ii[27], dian1, wd, weiduc2);
        int gaocheng2 = (ii[30] << 8) + ii[31];
        int sudu2 = (ii[32] << 8) + ii[33];
        int fangxiang2 = (ii[34] << 8) + ii[35];
        System.out.println("时间 2：" + result2 + "，经度" + jd2 + "，纬度" + wd2 + ",高程：" + gaocheng2
                + "米,速度：" + sudu2 + "节,航向：" + fangxiang2);

        // TODO: 2017/6/23 第三包位置信息
        long date3 = date_to + ii[36] * 1000;
        String result3 = sdf.format(new Date(date3));
        double jdc3 = (ii[37] << 16) + (ii[38] << 8) + ii[39];
        BigDecimal jd3 = getBigDecimal(ii[37], dian1, jd, jdc3);
        int weiduc3 = (ii[40] << 16) + (ii[41] << 8) + ii[42];
        BigDecimal wd3 = getBigDecimal(ii[40], dian1, wd, weiduc3);
        int gaocheng3 = (ii[43] << 8) + ii[44];
        int sudu3 = (ii[45] << 8) + ii[46];
        int fangxiang3 = (ii[47] << 8) + ii[48];
        System.out.println("时间 3：" + result3 + "，经度" + jd3 + "，纬度" + wd3 + ",高程：" + gaocheng3
                + "米,速度：" + sudu3 + "节,航向：" + fangxiang3);

        // TODO: 2017/6/23 第四包位置信息
        long date4 = date_to + ii[49] * 1000;
        String result4 = sdf.format(new Date(date4));
        double jdc4 = (ii[50] << 16) + (ii[51] << 8) + ii[52];
        BigDecimal jd4 = getBigDecimal(ii[50], dian1, jd, jdc4);
        int weiduc4 = (ii[53] << 16) + (ii[54] << 8) + ii[55];
        BigDecimal wd4 = getBigDecimal(ii[53], dian1, wd, weiduc4);
        int gaocheng4 = (ii[56] << 8) + ii[57];
        int sudu4 = (ii[58] << 8) + ii[59];
        int fangxiang4 = (ii[60] << 8) + ii[61];
        System.out.println("时间 4：" + result4 + "，经度" + jd4 + "，纬度" + wd4 + ",高程：" + gaocheng4
                + "米,速度：" + sudu4 + "节,航向：" + fangxiang4);


        // TODO: 2017/6/23 第五包位置信息
        long date5 = date_to + ii[62] * 1000;
        String result5 = sdf.format(new Date(date5));
        double jdc5 = (ii[63] << 16) + (ii[64] << 8) + ii[65];
        BigDecimal jd5 = getBigDecimal(ii[63], dian1, jd, jdc5);
        int weiduc5 = (ii[66] << 16) + (ii[67] << 8) + ii[68];
        BigDecimal wd5 = getBigDecimal(ii[66], dian1, wd, weiduc5);
        int gaocheng5 = (ii[69] << 8) + ii[70];
        int sudu5 = (ii[71] << 8) + ii[72];
        int fangxiang5 = (ii[73] << 8) + ii[74];
        System.out.println("时间 5：" + result5 + "，经度" + jd5 + "，纬度" + wd5 + ",高程：" + gaocheng5
                + "米,速度：" + sudu5 + "节,航向：" + fangxiang5);
//            (2017/6/21 22:12:10) 收到航空报文信息，发送方地址【358463】，飞行服务站ID【358464】:
//            时间：2017-06-21 22:11:20，经度：116.412144，纬度：39.99606，高程：130，速度：0，方向：69，存库成功
//            时间：2017-06-21 22:11:32，经度：116.41196，纬度：39.995848，高程：170，速度：1，方向：343，存库成功
//            时间：2017-06-21 22:11:44，经度：116.412144，纬度：39.995868，高程：183，速度：0，方向：284，存库成功
//            时间：2017-06-21 22:11:56，经度：116.412216，纬度：39.99598，高程：165，速度：2，方向：259，存库成功
//            时间：2017-06-21 22:12:08，经度：116.41232，纬度：39.996096，高程：130，速度：3，方向：41，存库成功

// $BDTXR,1,0358463,1,,BF01
// 057840
// 02C4BD08

// 06F04EF0
// 02624A9C
// 0082
// 0000
// 0045

// 0C
// FFFF48
// FFFF2C
// 00AA
// 0001
// 0157


// 18
// 000000
// FFFF40
// 00B7
// 0000
// 011C

// 24
// 000048
// FFFFB0
// 00A5
// 0002
// 0103

// 30
// 0000B0
// 000024
// 0082
// 0003
// 0029

// *30


        //经度108910136--116.413032
        //纬度34165672 --39.99642
        //高度0198-- 408米
        //速度0
        //航向00ea -- 234度
    }

    private static BigDecimal getBigDecimal(int i, BigDecimal dian1, BigDecimal jd, double jdc2) {
        byte[] bs = byteToBit((byte) i);
        BigDecimal jd2;
        if (bs[0] == 1) {
            jdc2 = jdc2 - 0xffffff;
            BigDecimal jdcbd = BigDecimal.valueOf(jdc2);
            BigDecimal jdc_bd2 = dian1.multiply(jdcbd).setScale(6, RoundingMode.HALF_UP);
            jd2 = jd.add(jdc_bd2);
        } else {
            BigDecimal jdcbd = BigDecimal.valueOf(jdc2);
            BigDecimal jdc_bd2 = dian1.multiply(jdcbd).setScale(6, RoundingMode.HALF_UP);
            jd2 = jd.add(jdc_bd2);
        }
        return jd2;
    }

    private static byte[] byteToBit(byte b) {
        byte[] bs = new byte[8];
        bs[0] = (byte) ((b >> 7) & 0x1);
        bs[1] = (byte) ((b >> 6) & 0x1);
        bs[2] = (byte) ((b >> 5) & 0x1);
        bs[3] = (byte) ((b >> 4) & 0x1);
        bs[4] = (byte) ((b >> 3) & 0x1);
        bs[5] = (byte) ((b >> 2) & 0x1);
        bs[6] = (byte) ((b >> 1) & 0x1);
        bs[7] = (byte) ((b >> 0) & 0x1);
        return bs;
    }

}
//(2017/6/23 15:06:32) 24 46 43 58 58 00 11 00 00 00 04 00 00 00 01 B2 87
//        (2017/6/23 15:07:32) 24 46 43 58 58 00 11 00 00 00 04 00 00 00 01 B2 87
//        (2017/6/23 15:08:32) 24 46 43 58 58 00 11 00 00 00 04 00 00 00 01 B2 87