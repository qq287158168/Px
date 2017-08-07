package com.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CollectionsTest {

    public static void main(String[] args) {

        List<Integer> a = new ArrayList<>();
        a.add(1);
        a.add(1);
        a.add(1);
        ArrayList<Integer> b = new ArrayList<>();
        b.add(2);
        b.add(2);
        b.add(2);
        a.addAll(b);

        java.util.Collections.reverse(a);
        java.util.Collections.addAll(a, 3, 3, 3);

        // CollectionsTest.fill(a, 0);

        java.util.Collections.shuffle(a);

        java.util.Collections.sort(a, new Comparator<Integer>() {

                    @Override
                    public int compare(Integer a, Integer b) {

                        if (a > b) {

                            return -1;
                        } else {
                            return 1;
                        }

                    }
                }

        );

        for (int i = 0; i < a.size(); i++) {
            System.out.println(a.get(i));
        }

    }

}
