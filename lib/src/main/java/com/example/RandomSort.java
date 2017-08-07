package com.example;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Admin on 2017/2/8.
 */

public class RandomSort {
    public static void main(String[] s) {
        Random random = new Random();
        int len = 6 + random.nextInt(5);
        int[] ran = new int[len];
        for (int i = 0; i < len; i++) {
            ran[i] = random.nextInt(100) + 1;
        }
        System.out.println("原始数组：" + Arrays.toString(ran));
        mySort(ran);
        System.out.println("排完之后：" + Arrays.toString(ran));
    }

    private static void mySort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            boolean f = false;
            for (int j = a.length - 1; j > i; j--) {
                if (a[j] < a[j - 1]) {
                    int x = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = x;
                    f = true;
                }
            }
            if (!f) {
                break;
            }
            System.out.println("---------" + Arrays.toString(a));
        }
    }




}
