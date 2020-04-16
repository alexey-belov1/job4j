package ru.job4j.parking;

import java.util.Map;

/**
 * Common parking, which consist of PassengersParking and TrucksParking
 */
public class CommonParking extends PassengerParking {

    /**
     * Pairs: key - place in parking;
     *        value - Car.
     */
    private Map<Integer, Car> truckPark;

    public CommonParking(int sizePassengerParking, int sizeTruckParking) {
        super(sizePassengerParking);
        for (int i = 1; i <= sizeTruckParking; i++) {
            truckPark.put(i, null);
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
