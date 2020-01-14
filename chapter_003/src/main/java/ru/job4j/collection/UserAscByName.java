package ru.job4j.collection;

import java.util.Comparator;

public class UserAscByName implements Comparator<User> {
    @Override
    public int compare(User us1, User us2) {
        return us1.getName().compareTo(us2.getName());
    }
}
