package ru.job4j.array;

public class Defragment {
    public static String[] compress(String[] array) {

        for (int index = 0; index < array.length; index++) {
            if (array[index] == null) {
                int i = index + 1;
                while (i < array.length) {
                    if (array[i] != null) {
                        array[index] = array[i];
                        array[i] = null;
                        break;
                    }
                    i++;
                }
            }
        }
        return array;
    }
        /*
        for(int i = index + 1; i < array.length; i++){
            if(array[i] != null) {
                 array[index] = array[i];
                 array[i] = null;
                 break;
             }
        }
        */

    public static void main(String[] args) {
        String[] input = {"I", null, "wanna", null, "be", null, "compressed"};
        String[] compressed = compress(input);
        System.out.println();
        for (int index = 0; index < compressed.length; index++) {
            System.out.print(compressed[index] + " ");
        }
    }
}