package ru.job4j.collection;

import java.util.Comparator;

public class UserDescByAge implements Comparator<User> {
    @Override
    public int compare(User us1, User us2) {
        return Integer.compare(us2.getAge(), us1.getAge());
    }
}
