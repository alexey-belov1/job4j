package ru.job4j.loop;

public class Mortgage {
    public int year(int amount, int salary, double percent) {
        int year = 0;
        if(amount != 0){
            double balance = amount * percent / 100.0 + amount - salary;
            if(balance <= 0){
                year = 1;
            }
            else{
                year = 1;
                while(balance > 0){
                    balance = balance * percent / 100.0 + balance - salary;
                    year++;
                }
            }
        }


        return year;
    }
}