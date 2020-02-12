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

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    /*
    Задача: 2. Не перекрывать equals hashCode
    В карту добавлены две записи, поскольку их ключи не равны. В данном случае ключами являются объекты, для которых не переопределены методы hashCode и equals.
     По умолчанию метод hashCode преобразовывает адрес объекта в целочисленное значение, а метод equals сравнивает адреса объектов в памяти.
     Объекты имеют разные адреса в памяти, и следовательно не равны друг другу.
     p.s.:Если хэшкоды объектов разные, то и объекты разные. Если хэшкоды объектов равны, то объекты не всегда равны.
    В данном случае объекты имеют разные хэшкоды, и следовательно объекты не равны (метод equals при добавлении в Map вызываться даже не будет, хотя тоже вернул бы false).
     */


    /*
    Задача: 3. Переопределить только hashCode
    В карту добавлены две записи, поскольку их ключи не равны. После переопределения методоа hashCode объекты user1 и user2 имеют одинаковые хэшкоды, т.к. имеют одинаковые значения полей.
    Однако равенства хэшкодов недостаточно, чтобы признать, что ключи равны друг другу.
    Метод equals для данных объектов по-умолчанию вернет значение false, поскольку будет сравнивать адреса объектов в памяти.
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
