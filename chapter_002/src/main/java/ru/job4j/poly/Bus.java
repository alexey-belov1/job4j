package ru.job4j.poly;

public class Bus implements Transport {
    @Override
    public void drive() {
        System.out.println("Автобус поехал");
    }

    @Override
    public void passengers(int count) {
        System.out.println("В автобусе " + count + " пассажиров");
    }

    @Override
    public double fill(double fuel) {
        return fuel * 45.0;
    }
}
