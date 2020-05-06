package ru.job4j.parking;

import java.util.Objects;

/**
 * Abstract class which describes the basic behavior of vehicles
 */
abstract class Vehicle {

    /**
     * Vehicle name
     */
    private String name;

    /**
     * Registration number
     */
    private String number;

    /**
     * Area of the vehicle
     */
    private int area;

    public Vehicle(String name, String number, int area) {
        this.name = name;
        this.number = number;
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public int getArea() {
        return area;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vehicle vehicle = (Vehicle) o;
        return this.number.equals(vehicle.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
