package ru.job4j.parking;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class CommonParkingTest {

    @Test
    public void whenRideInCar() {
        Parking parking = new CommonParking(1, 0);
        Vehicle toyota = new Car("toyota", "x333xx");
        Vehicle honda = new Car("honda", "x444xx");
        assertThat(parking.rideIn(toyota), is(true));
        assertThat(parking.freePlacesForCar(), is(0));
        assertThat(parking.rideIn(honda), is(false));
    }

    @Test
    public void whenRideOutCar() {
        Parking parking = new CommonParking(1, 0);
        Vehicle toyota = new Car("toyota", "x333xx");
        parking.rideIn(toyota);
        assertThat(parking.rideOut(toyota), is(true));
        assertThat(parking.freePlacesForCar(), is(1));
        assertThat(parking.rideOut(toyota), is(false));
    }

    @Test
    public void whenRideInTruck() {
        Parking parking = new CommonParking(0, 1);
        Vehicle kamaz = new Truck("kamaz", "x333xx", 2);
        Vehicle isuzu = new Truck("isuzu", "x444xx", 2);
        assertThat(parking.rideIn(kamaz), is(true));
        assertThat(parking.freePlacesForTruck(), is(0));
        assertThat(parking.rideIn(isuzu), is(false));
    }

    @Test
    public void whenRideOutTruck() {
        Parking parking = new CommonParking(0, 1);
        Vehicle kamaz = new Truck("kamaz", "x333xx", 2);
        parking.rideIn(kamaz);
        assertThat(parking.rideOut(kamaz), is(true));
        assertThat(parking.freePlacesForTruck(), is(1));
        assertThat(parking.rideOut(kamaz), is(false));
    }

    @Test
    public void whenRideInTruckOnCarParking() {
        Parking parking = new CommonParking(2, 0);
        Vehicle kamaz = new Truck("kamaz", "x333xx", 2);
        assertThat(parking.rideIn(kamaz), is(true));
        assertThat(parking.freePlacesForCar(), is(0));
    }

    @Test
    public void whenRideInTruckOnCarParkingAndThemRideOut() {
        Parking parking = new CommonParking(2, 0);
        Vehicle kamaz = new Truck("kamaz", "x333xx", 2);
        parking.rideIn(kamaz);
        assertThat(parking.rideOut(kamaz), is(true));
        assertThat(parking.freePlacesForCar(), is(2));
    }

    @Test
    public void whenTryRideInCarOnTruckParking() {
        Parking parking = new CommonParking(0, 1);
        Vehicle toyota = new Car("toyota", "x333xx");
        assertThat(parking.rideIn(toyota), is(false));
    }

    @Test
    public void whenRideInTruckOnTruckParkingAndCarParking() {
        Parking parking = new CommonParking(2, 1);
        Vehicle kamaz = new Truck("kamaz", "x333xx", 2);
        Vehicle isuzu = new Truck("isuzu", "x444xx", 2);
        parking.rideIn(kamaz);
        parking.rideIn(isuzu);
        assertThat(parking.freePlacesForTruck(), is(0));
        assertThat(parking.freePlacesForCar(), is(0));
    }

    @Test
    public void whenRideInTruckOnCarParkingAndHasNearbyPlaces() {
        Parking parking = new CommonParking(4, 0);
        Vehicle toyota = new Car("toyota", "x333xx");
        Vehicle honda = new Car("honda", "x444xx");
        Vehicle kamaz = new Truck("kamaz", "x555xx", 2);
        parking.rideIn(toyota);
        parking.rideIn(honda);
        assertThat(parking.rideIn(kamaz), is(true));
        assertThat(parking.freePlacesForCar(), is(0));
    }

    @Test
    public void whenRideInTruckOnCarParkingAndHasNotNearbyPlaces() {
        Parking parking = new CommonParking(4, 0);
        Vehicle ford = new Car("ford", "x222xx");
        Vehicle toyota = new Car("toyota", "x333xx");
        Vehicle honda = new Car("honda", "x444xx");
        Vehicle kamaz = new Truck("kamaz", "x555xx", 2);
        parking.rideIn(ford);
        parking.rideIn(toyota);
        parking.rideIn(honda);
        parking.rideOut(toyota);
        assertThat(parking.rideIn(kamaz), is(false));
        assertThat(parking.freePlacesForCar(), is(2));
    }
}