package ru.job4j.array;

public class Turn {
    public int[] back(int[] array) {
        int lenthMid = (array.length % 2 == 0) ? (array.length / 2) : ((array.length - 1) / 2);
        int temp;
        for (int i = 0; i < lenthMid; i++) {
            temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }
        return array;
    }
}