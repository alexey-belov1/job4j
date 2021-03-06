package ru.job4j.collection;

import java.util.Comparator;

public class UserDescByName implements Comparator<User> {
    @Override
    public int compare(User us1, User us2) {
        return us2.getName().compareTo(us1.getName());
    }
}
