package com.sby1027.px;

import android.app.Application;

/**
 * Created by Admin on 2017/3/7.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        String a = "1234567890";
        int alen = a.length();
        String b = "一二三四五六七八九十";
        System.out.println(b);
        int blen = b.length();
        String c = "abcdefghij";
        int clen = c.length();
        String d = ",.!@$";
        int dlen = d.length();

    }
}
