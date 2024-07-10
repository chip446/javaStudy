package com.chip.rentcar.vehicle;

public interface IVehicle {
    Long getId();
    String getCompany();
    String getModel();
    int getMakeYear();
    VehicleType getType();
    String getColor();
    int getTotalKm();
    String getFactoryNumber();
    String getRegistNumber();
    VehicleStatus getStatus();
    void setId(Long id);
    void setCompany(String company);
    void setModel(String model);
    void setMakeYear(int year);
    void setType(VehicleType type);
    void setColor(String color);
    void setTotalKm(int km);
    void setFactoryNumber(String factoryNumber);
    void setRegistNumber(String registNumber);
    void setStatus(VehicleStatus status);
}