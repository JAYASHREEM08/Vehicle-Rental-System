package com.wipro.vehiclerental.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vehicle")
public class Vehicle {

    @Id
    private String vehicleId;

    private String vehicleNumber;
    private String vehicleType;
    private Integer rentPerDay;
    private String branchId;

    public Vehicle() {
    }

    public Vehicle(String vehicleId, String vehicleNumber,
                   String vehicleType, Integer rentPerDay,
                   String branchId) {
        this.vehicleId = vehicleId;
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        this.rentPerDay = rentPerDay;
        this.branchId = branchId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Integer getRentPerDay() {
        return rentPerDay;
    }

    public void setRentPerDay(Integer rentPerDay) {
        this.rentPerDay = rentPerDay;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }
}
