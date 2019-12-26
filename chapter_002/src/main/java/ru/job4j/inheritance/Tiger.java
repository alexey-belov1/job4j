package ru.job4j.inheritance;

public class Tiger extends Predator {
    public Tiger(String name) {
        super(name);
        System.out.println("Load Tiger");
    }

    public static void main(String[] args) {
        Tiger tiger = new Tiger("Pursh");
    }
}
