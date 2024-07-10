package com.chip.rentcar.vehicle;

public interface IVehicleService {
    Vehicle addVehicle(Vehicle vehicle);
    boolean removeVehicle(Long id);
    Vehicle updateVehicle(Vehicle vehicle);
    Vehicle findVehicleById(Long id);
    List<Vehicle> getAllVehicle();
    List<Vehicle> findVehicleForStatus(VehicleStatus status);
}