package com.example;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

/**
 * Created by Admin on 2017/2/9.
 */

public class ZhuanShu {
    public static void main(String[] s) throws UnsupportedEncodingException {

       String tempstr = "" + 3 + 9 + "бу" + 59.7881;

        int dian = tempstr.indexOf(".") + 1;
        String miao = tempstr.substring(dian);
        double miaoInt = Double.parseDouble(miao);
        BigDecimal bigDecimal = BigDecimal.valueOf(miaoInt);
        BigDecimal bg6 = BigDecimal.valueOf(0.006);
        BigDecimal bgmiao = bigDecimal.multiply(bg6).setScale(2,BigDecimal.ROUND_HALF_UP);

    }

}
