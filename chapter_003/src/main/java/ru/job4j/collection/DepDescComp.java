package ru.job4j.collection;

import java.util.Comparator;

public class DepDescComp implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {

        int res = 0;
        int size = Math.min(o1.length(), o2.length());
        for (int i = 0; i < size && res == 0; i++) {
            res = Character.compare(o2.charAt(i), o1.charAt(i));
        }
        if (res == 0) {
            res = Integer.compare(o1.length(), o2.length());
        }

        return res;
    }
}