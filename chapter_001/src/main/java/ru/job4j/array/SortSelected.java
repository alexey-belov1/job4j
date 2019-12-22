package ru.job4j.array;

public class SortSelected {

    public static int[] sort(int[] data) {
        int min, index, temp;
        FindLoop findloop = new FindLoop();

        for (int i = 0; i < data.length; i++) {
            min = MinDiapason.findMin(data, i, data.length - 1);
            index = findloop.indexOf(data, min, i, data.length - 1);
            temp = data[i];
            data[i] = data[index];
            data[index] = temp;
        }

        return data;
    }
}