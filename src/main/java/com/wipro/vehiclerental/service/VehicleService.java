package com.wipro.vehiclerental.service;

import com.wipro.vehiclerental.entity.Vehicle;
import com.wipro.vehiclerental.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    // Get all vehicles
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    // Get vehicle by ID
    public Optional<Vehicle> getVehicleById(String id) {
        return vehicleRepository.findById(id);
    }

    // Add / Update vehicle
    public Vehicle saveVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    // Delete vehicle
    public void deleteVehicle(String id) {
        vehicleRepository.deleteById(id);
    }

    // WHERE
    public List<Vehicle> getVehiclesAbove1000() {
        return vehicleRepository.findVehiclesAbove1000();
    }

    // LIKE
    public List<Vehicle> getVehiclesByNumber(String number) {
        return vehicleRepository.findVehiclesByNumber(number);
    }

    // ORDER BY
    public List<Vehicle> getVehiclesOrderByRent() {
        return vehicleRepository.findVehiclesOrderByRent();
    }

    // GROUP BY
    public List<Object[]> countVehiclesByType() {
        return vehicleRepository.countVehiclesByType();
    }

    // HAVING
    public List<Object[]> findVehicleTypesWithMoreThanOne() {
        return vehicleRepository.findVehicleTypesWithMoreThanOne();
    }

    // AVG
    public Double findAverageRent() {
        return vehicleRepository.findAverageRent();
    }

    // MAX
    public List<Vehicle> findVehicleWithHighestRent() {
        return vehicleRepository.findVehicleWithHighestRent();
    }

    // Subquery - AVG
    public List<Vehicle> findVehiclesAboveAverage() {
        return vehicleRepository.findVehiclesAboveAverage();
    }

    // ANY
    public List<Vehicle> findVehiclesGreaterThanAnyBike() {
        return vehicleRepository.findVehiclesGreaterThanAnyBike();
    }

    // ALL
    public List<Vehicle> findVehiclesGreaterThanAllBikes() {
        return vehicleRepository.findVehiclesGreaterThanAllBikes();
    }

    // EXISTS
    public List<Vehicle> findRentedVehicles() {
        return vehicleRepository.findRentedVehicles();
    }

    // NOT EXISTS
    public List<Vehicle> findNeverRentedVehicles() {
        return vehicleRepository.findNeverRentedVehicles();
    }
}