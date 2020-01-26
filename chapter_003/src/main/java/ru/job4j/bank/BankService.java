package ru.job4j.bank;

import java.util.*;

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
        Account acccountOut = null;
        User user = findByPassport(passport);
        List<Account> accounts = user != null ? this.users.get(user) : Collections.emptyList();
        for (Account account : accounts) {
            if (account.getRequisite().equals(requisite)) {
                acccountOut = account;
                break;
            }
        }
        return acccountOut;
    }

    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        Account srcAccount = findByRequisite(srcPassport, srcRequisite);
        Account destAccount = findByRequisite(destPassport, destRequisite);
        return srcAccount != null
                && destAccount != null
                && srcAccount.transfer(destAccount, amount);
    }
}