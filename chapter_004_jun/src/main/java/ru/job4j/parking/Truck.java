package ru.job4j.parking;

/**
 * Trucks
 */
public class Truck extends Vehicle {

    public Truck(String name, String number, int area) {
        super(name, number, area);
        if (area < 1) {
            throw new IllegalStateException("Invalid value for area.");
        }
    }
}
