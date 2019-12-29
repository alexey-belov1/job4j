package ru.job4j.inheritance;

public class Predator extends Animal {

    public Predator() {
        System.out.println("Load Predator without name");
    }

    public Predator(String name) {
        super(name);
        System.out.println("Load Predator with name " + name);
    }
}
