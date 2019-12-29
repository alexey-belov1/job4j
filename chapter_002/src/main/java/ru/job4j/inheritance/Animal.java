package ru.job4j.inheritance;

public class Animal {
    private String name;

    public Animal() {
        System.out.println("Load Animal without name");
    }

    public Animal(String name) {
        this.name = name;
        System.out.println("Load Animal with name " + name);
    }
}
