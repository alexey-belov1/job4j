package ru.job4j.map;

import java.util.*;

public class User {
    public String name;
    public int children;
    public Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }
    /*В карту добавлены две записи, поскольку их ключи не равны. В данном случае ключами являются объекты, для которых не переопределены методы hashCode и equals.
     По умолчанию метод hashCode преобразовывает адрес объекта в целочисленное значение, а метод equals сравнивает адреса объектов в памяти.
     Объекты имеют разные адреса в памяти, и следовательно не равны друг другу.
     */
    public static void main(String[] args) {
        User user1 = new User("Vasya", 2, new GregorianCalendar(1990, Calendar.JANUARY, 1));
        User user2 = new User("Vasya", 2, new GregorianCalendar(1990, Calendar.JANUARY, 1));
        Map<User, Object> users = new HashMap<>();
        users.put(user1, "first");
        users.put(user2, "second");
        System.out.println(users);
    }
}
