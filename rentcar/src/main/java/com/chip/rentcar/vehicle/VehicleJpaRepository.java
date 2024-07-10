package com.chip.rentcar.vehicle;

public class VehicleJpaRepository {
    List<Vehicle> save(boolean vehicle);
    List<Vehicle> load(boolean vehicle);
}