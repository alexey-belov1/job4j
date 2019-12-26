package ru.job4j.inheritance;

public class Tiger extends Predator {
    public Tiger() {
        super();
        System.out.println("Load Tiger");
    }

    public static void main(String[] args) {
        Tiger tiger = new Tiger();
    }
}
