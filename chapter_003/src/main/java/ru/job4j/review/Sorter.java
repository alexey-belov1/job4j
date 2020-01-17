package ru.job4j.review;

import java.util.*;

public class Sorter {

    //Нет смысла объявлять пустой конструктор без параметров
    public Sorter() {

    }

    //Не указан модификатор доступа
    Set<User> sort(List<User> list) {
        TreeSet<User> sortedList = new TreeSet<>();
        sortedList.addAll(list);
        return sortedList;
    }

    //Не указан модификатор доступа
    //Название метода из нескольких слов не содержит заглавных букв
    List<User> sortnamelength(List<User> list) {
        Comparator<User> compar = new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getName().length() - o2.getName().length();
            }
        };
        list.sort(compar);
        return list;
    }

    //Не указан модификатор доступа
    //Название метода из нескольких слов не содержит заглавных букв
    List<User> sortboth(List<User> list) {
        Comparator<User> compar1 = new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };
        Comparator<User> compar2 = new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getAge() - o2.getAge();
            }
        };
        list.sort(compar1.thenComparing(compar2));
        return list;
    }
}