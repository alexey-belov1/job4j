package ru.job4j.parking;

import java.util.HashMap;
import java.util.Map;

/**
 * Common parking, which consist of PassengersParking and TrucksParking
 */
public class CommonParking implements Parking {

    /**
     * Parking for passenger cars
     * Pairs: key - place in parking;
     *        value - Car.
     */
    private Map<Integer, Car> passengerCarPark;

    /**
     * Parking for trucks
     * Pairs: key - place in parking;
     *        value - Car.
     */
    private Map<Integer, Car> truckPark;

    public CommonParking(int sizePassengerParking, int sizeTruckParking) {
        this.passengerCarPark = new HashMap<>(sizePassengerParking);
        for (int i = 1; i <= sizePassengerParking; i++) {
            this.passengerCarPark.put(i, null);
        }

        this.truckPark = new HashMap<>(sizeTruckParking);
        for (int i = 1; i <= sizeTruckParking; i++) {
            this.truckPark.put(i, null);
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
    public int freePlacesForPassengerCar() {
        return 0;
    }

    @Override
    public int freePlacesForTruck() {
        return 0;
    }
}
