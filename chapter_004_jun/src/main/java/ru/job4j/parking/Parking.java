package ru.job4j.parking;

public interface Parking {

    /**
     * Get in on the parking
     * @param car Car
     * @return return false if impossible
     */
    boolean rideIn(Car car);

    /**
     * Get out of the parking
     * @param car Car
     * @return return false if car not found
     */
    boolean rideOut(Car car);

    /**
     * Get free places on the parking
     * @return free places
     */
    int freePlaces();
}
