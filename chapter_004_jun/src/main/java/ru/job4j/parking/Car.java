package ru.job4j.parking;

import java.util.Objects;

/**
 * Abstract class which describes the basic behavior of cars
 */
abstract class Car {

    /**
     * Car name
     */
    private String name;

    /**
     * Registration number
     */
    private String number;

    /**
     * Area of the car
     */
    private int area;

    public Car(String name, String number, int area) {
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
        Car car = (Car) o;
        return this.number.equals(car.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
