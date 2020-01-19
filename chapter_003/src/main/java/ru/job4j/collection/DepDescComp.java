package ru.job4j.collection;

import java.util.Comparator;

public class DepDescComp implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {

        String[] o1str = o1.split("/");
        String[] o2str = o2.split("/");
        int res = 0;
        int size = Math.min(o1str.length, o2str.length);
        for (int i = 0; i < size; i++) {
            res = o2str[i].compareTo(o1str[i]);
            if (res != 0) {
                break;
            }
        }
        if (res == 0) {
            res = Integer.compare(o1str.length, o2str.length);
        }

        return res;
    }
}