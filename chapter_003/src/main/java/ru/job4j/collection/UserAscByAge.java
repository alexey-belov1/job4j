package ru.job4j.collection;

import java.util.Comparator;

public class UserAscByAge implements Comparator<User> {
    @Override
    public int compare(User us1, User us2) {
        return Integer.compare(us1.getAge(), us2.getAge());
    }
}
