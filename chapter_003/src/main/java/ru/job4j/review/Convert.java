package ru.job4j.review;

import java.util.*;

public class Convert {

    //Нет смысла объявлять пустой конструктор без параметров
    public Convert() {

    }

    //Не указан модификатор доступа
    //Лучше использовать скобки "{...}" в циклах
    //Можно было использовать цикл foreach
    //Converts array to list
    List<Integer> makeList(int[][] array) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                list.add(array[i][j]);
            }
        }
        return list;
    }

    // Можно было использовать метод Math.ceil() для округления в большую сторону
    //Лучше использовать скобки "{...}" в условиях
    //Вместо if можно было использовать тернарное условие
    //Converts list to array
    public int[][] makeArray(List<Integer> list, int rws) {
        Iterator<Integer> iterator = list.iterator();

        int cls = list.size() / rws + (list.size() % rws == 0 ? 0 : 1);

        int[][] array = new int[rws][cls];
        for (int i = 0; i < rws; i++) {
            for (int j = 0; j < cls; j++) {
                if (iterator.hasNext()) {
                    array[i][j] = iterator.next();
                } else {
                    array[i][j] = 0;
                }
            }
        }
        return array;
    }
}