package ru.job4j.synch;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.*;

@ThreadSafe
public class UserStorage {

    @GuardedBy("this")
    private Map<Integer, User> users = new HashMap<>();

    public synchronized boolean add(User user) {
        this.users.put(user.getId(), user);
        return this.users.containsKey(user.getId());
    }

    public synchronized boolean delete(User user) {
        return this.users.remove(user.getId(), user);
    }

    public synchronized void transfer(int fromId, int toId, int amount) {
        User user1 = users.get(fromId);
        User user2 = users.get(toId);
        if (user1 != null && user2 != null) {
            user1.setAmount(user1.getAmount() - amount);
            user2.setAmount(user2.getAmount() + amount);
        }
    }
}
