package com.example;

import java.util.ArrayList;

/**
 * Created by Admin on 2017/4/8.
 */

public class ListTest {

    private static int anInt;
    private static int anInt2;
    private static ArrayList<Integer> integers = new ArrayList<>();

    public static void main(String[] arg) {


        new Thread() {
            @Override
            public void run() {
                super.run();


                while (true) {

                    try {
                        anInt++;
                        integers.add(anInt);

                        for (int i = 0; i <integers.size() ; i++) {

                            System.out.print(integers.get(i) + ",");
                        }
                        System.out.println("");
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


            }
        }.start();


        new Thread() {
            @Override
            public void run() {
                super.run();


                while (true) {

                    try {
                        Thread.sleep(20);
                        System.out.println(integers.get(0)+"------");
                        integers.remove(0);
//                        for (int i = 0; i <integers.size() ; i++) {
//
//                            System.out.print(integers.get(i) + "--");
//                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


            }
        }.start();



    }
}
