package ru.job4j.sort;

import java.util.Arrays;

public class Merge {

    public int[] merge(int[] left, int[] right) {
        int[] rsl = new int[left.length + right.length];
        int i = 0;
        int indexleft = 0;
        int indexright = 0;
        while (i < rsl.length) {
            if (indexleft == left.length) {
                rsl[i] = right[indexright];
                indexright++;
            } else if (indexright == right.length) {
                rsl[i] = left[indexleft];
                indexleft++;
            } else {
                if (left[indexleft] < right[indexright]) {
                    rsl[i] = left[indexleft];
                    indexleft++;
                } else {
                    rsl[i] = right[indexright];
                    indexright++;
                }
            }
            i++;
        }
        return rsl;
    }

    public static void main(String[] args) {
        Merge process = new Merge();
        int[] rsl = process.merge(
                new int[] {1, 3, 5},
                new int[] {2, 4}
        );
        System.out.println(Arrays.toString(rsl));
    }
}
