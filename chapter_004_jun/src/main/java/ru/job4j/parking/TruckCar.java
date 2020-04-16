package ru.job4j.parking;

/**
 * Trucks
 */
public class TruckCar extends Car {

    public TruckCar(String name, String number, int area) {
        super(name, number, area);
        if (area < 1) {
            throw new IllegalStateException("Invalid value for area.");
        }
    }
}
