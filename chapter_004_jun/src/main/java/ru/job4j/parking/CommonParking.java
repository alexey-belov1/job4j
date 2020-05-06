package ru.job4j.parking;

import java.util.*;

/**
 * Common parking, which consist of CarsParking and TrucksParking
 */
public class CommonParking implements Parking {

    /**
     * Parking for cars
     * Pairs: key - place in parking;
     *        value - Vehicle.
     */
    private Map<Integer, Vehicle> cars;

    /**
     * Parking for trucks
     * Pairs: key - place in parking;
     *        value - Vehicle.
     */
    private Map<Integer, Vehicle> trucks;

    public CommonParking(int sizeCars, int sizeTrucks) {
        this.cars = new HashMap<>(sizeCars);
        for (int i = 1; i <= sizeCars; i++) {
            this.cars.put(i, null);
        }

        this.trucks = new HashMap<>(sizeTrucks);
        for (int i = 1; i <= sizeTrucks; i++) {
            this.trucks.put(i, null);
        }
    }

    @Override
    public boolean rideIn(Vehicle vehicle) {
        boolean result;
        if (vehicle.getArea() == 1) {
            result = putInParking(this.cars, vehicle);
        } else {
            result = putInParking(this.trucks, vehicle);
            if (!result) {
                int area = vehicle.getArea();
                for (List<Integer> places : nearbyPlaces()) {
                    if (places.size() >= area) {
                        for (int i = 0; i < area; i++) {
                            this.cars.put(places.get(i), vehicle);
                        }
                        result = true;
                        break;
                    }
                }
            }
        }
        return result;
    }

    private boolean putInParking(Map<Integer, Vehicle> parking, Vehicle vehicle) {
        boolean result = false;
        for (Integer place : parking.keySet()) {
            if (parking.get(place) == null) {
                parking.put(place, vehicle);
                result = true;
                break;
            }
        }
        return result;
    }

    private List<List<Integer>> nearbyPlaces() {
        List<List<Integer>> places = new ArrayList<>();
        List<Integer> nearby = new ArrayList<>();
        for (Integer place : this.cars.keySet()) {
            if (this.cars.get(place) == null) {
                nearby.add(place);
            } else {
                if (nearby.size() > 0) {
                    places.add(nearby);
                    nearby.clear();
                }
            }
        }
        if (nearby.size() > 0) {
            places.add(nearby);
        }

        places.sort(Comparator.comparing(List::size));
        return places;
    }

    @Override
    public boolean rideOut(Vehicle vehicle) {
        boolean result;
        if (vehicle.getArea() == 1) {
            result = removeFromParking(this.cars, vehicle);
        } else {
            result = removeFromParking(this.trucks, vehicle);
            if (!result) {
                result = removeFromParking(this.cars, vehicle);
            }
        }
        return result;
    }

    private boolean removeFromParking(Map<Integer, Vehicle> parking, Vehicle vehicle) {
        boolean result = false;
        for (Integer place : parking.keySet()) {
            if (parking.get(place) != null && parking.get(place).equals(vehicle)) {
                parking.replace(place, null);
                result = true;
            }
        }
        return result;
    }

    @Override
    public int freePlacesForCar() {
        return freePlaces(this.cars);
    }

    @Override
    public int freePlacesForTruck() {
        return freePlaces(this.trucks);
    }

    private int freePlaces(Map<Integer, Vehicle> parking) {
        int size = 0;
        for (Integer place : parking.keySet()) {
            size = (parking.get(place) == null) ? size + 1 : size;
        }
        return size;
    }
}
