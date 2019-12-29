package ru.job4j.inheritance;

public class Tiger extends Predator {
    public Tiger() {
        System.out.println("Load Tiger without name");
    }

    public Tiger(String name) {
        super(name);
        System.out.println("Load Tiger with name " + name);
    }

    public static void main(String[] args) {
        Tiger tiger1 = new Tiger();
        Tiger tiger2 = new Tiger("Pursh");
    }
}
