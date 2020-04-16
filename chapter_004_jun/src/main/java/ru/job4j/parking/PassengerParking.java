package ru.job4j.parking;

import java.util.*;

/**
 * Parking for passenger cars and trucks
 */
public class PassengerParking implements Parking {

    /**
     * Pairs: key - place in parking;
     *        value - Car.
     */
    private Map<Integer, Car> park;

    public PassengerParking(int size) {
        park = new HashMap<>(size);
        for (int i = 1; i <= size; i++) {
            park.put(i, null);
        }
    }

    @Override
    public boolean rideIn(Car car) {
        return false;
    }

    @Override
    public boolean rideOut(Car car) {
        return false;
    }

    @Override
    public int freePlaces() {
        return 0;
    }
}
