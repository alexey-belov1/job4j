package ru.job4j.parking;

public interface Parking {

    /**
     * Get in on the parking
     * @param vehicle Vehicle
     * @return return false if impossible
     */
    boolean rideIn(Vehicle vehicle);

    /**
     * Get out of the parking
     * @param vehicle Vehicle
     * @return return false if car not found
     */
    boolean rideOut(Vehicle vehicle);

    /**
     * Get free places on the parking for car
     * @return free places
     */
    int freePlacesForCar();

    /**
     * Get free places on the parking for truck
     * @return free places
     */
    int freePlacesForTruck();
}
