package ru.job4j.review;


public class Account {

    //Не указаны модификаторы доступа private
    double values;
    String reqs;

    public Account(double values, String requisites) {
        this.values = values;
        this.reqs = requisites;
    }

    public double getValues() {
        return this.values;
    }

    public String getReqs() {
        return this.reqs;
    }

    boolean transfer(Account destination, double amount) {
        boolean success = false;
        if (amount > 0 && amount < this.values && destination != null) {
            success = true;
            this.values -= amount;
            destination.values += amount;
        }
        return success;
    }

    //Можно проще сделать: return "Account{" + "values=" + values + ", reqs='" + reqs + "\\" + "}";
    public String toString() {
        String otvet;
        otvet = "Account{" + "values=" + values + ", reqs='" + reqs + "\\" + "}";
        return otvet;
    }

    //Отсутствует аннотация @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Account account = (Account) o;

        return this.reqs.equals(account.reqs);
    }

    //Отсутствует аннотация @Override
    public int hashCode() {
        return this.reqs.hashCode();
    }
}