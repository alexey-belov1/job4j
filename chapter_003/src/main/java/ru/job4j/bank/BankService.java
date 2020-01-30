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
        return this.users.keySet().stream()
                .filter(x -> x.getPassport().equals(passport))
                .findAny()
                .orElse(null);
    }

    public Account findByRequisite(String passport, String requisite) {
        User user = findByPassport(passport);
        List<Account> accounts = user != null ? this.users.get(user) : Collections.emptyList();
        return accounts.stream()
                .filter(x -> x.getRequisite().equals(requisite))
                .findAny()
                .orElse(null);
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