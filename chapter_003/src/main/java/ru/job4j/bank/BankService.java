package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankService {
    private Map<User, List<Account>> users = new HashMap<>();

    public void addUser(User user) {
        this.users.putIfAbsent(user, new ArrayList<>());
    }

    public void deleteUser(User user) {
        this.users.remove(user);
    }

    public void addAccount(String passport, Account account) {
        User user = findByPassport(passport);
        if (user != null) {
            this.users.get(user).add(account);
        }
    }

    public User findByPassport(String passport) {
        User userOut = null;
        for (User user : this.users.keySet()) {
            if (user.getPassport().equals(passport)) {
                userOut = user;
                break;
            }
        }
        return userOut;
    }

    public Account findByRequisite(String passport, String requisite) {
        User user = findByPassport(passport);
        int index = this.users.get(user).indexOf(new Account(requisite, -1));
        return (index != -1) ? this.users.get(user).get(index) : null;
    }

    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        boolean rsl = false;
        Account srcAccount = findByRequisite(srcPassport, srcRequisite);
        Account destccount = findByRequisite(destPassport, destRequisite);
        if (srcAccount != null
            && destccount != null
            && srcAccount.getBalance() - amount >= 0) {
            srcAccount.setBalance(srcAccount.getBalance() - amount);
            destccount.setBalance(destccount.getBalance() + amount);
                rsl = true;
        }
        return rsl;
    }
}