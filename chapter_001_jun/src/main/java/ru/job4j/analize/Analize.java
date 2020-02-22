package ru.job4j.analize;

import java.util.*;

public class Analize {

    public Info diff(List<User> previous, List<User> current) {

        int added;
        int changed = 0;
        int deleted;
        int equalCount = 0;

        for (User userPre : previous) {
            for (User userCur : current) {
                if (userCur.id == userPre.id) {
                    if (userCur.name.equals(userPre.name)) {
                        equalCount++;
                    } else {
                        changed++;
                    }
                }
            }
        }

        deleted = previous.size() - equalCount - changed;
        added = current.size() - equalCount - changed;

        return new Info(added, changed, deleted);
    }

    public static class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public static class Info {
        int added;
        int changed;
        int deleted;

        public Info(int added, int changed, int deleted) {
            this.added = added;
            this.changed = changed;
            this.deleted = deleted;
        }
    }
}