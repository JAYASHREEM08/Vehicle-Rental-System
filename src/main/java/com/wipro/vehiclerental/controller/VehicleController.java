package com.wipro.vehiclerental.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.vehiclerental.entity.Vehicle;
import com.wipro.vehiclerental.service.VehicleService;

@RestController
@RequestMapping("/api/v1/vehicles")
@CrossOrigin(origins = "http://localhost:4200")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    // Get all vehicles
    @GetMapping
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    // Get vehicle by ID
    @GetMapping("/{id}")
    public Vehicle getVehicleById(@PathVariable String id) {
        return vehicleService.getVehicleById(id)
                .orElseThrow(() ->
                        new RuntimeException("Vehicle not found with id: " + id));
    }

    // Add vehicle
    @PostMapping
    public Vehicle createVehicle(@RequestBody Vehicle vehicle) {
        return vehicleService.saveVehicle(vehicle);
    }

    // Update vehicle
    @PutMapping("/{id}")
    public Vehicle updateVehicle(@PathVariable String id,
                                 @RequestBody Vehicle vehicle) {

        Vehicle existingVehicle = vehicleService.getVehicleById(id)
                .orElseThrow(() ->
                        new RuntimeException("Vehicle not found with id: " + id));

        existingVehicle.setVehicleNumber(vehicle.getVehicleNumber());
        existingVehicle.setVehicleType(vehicle.getVehicleType());
        existingVehicle.setRentPerDay(vehicle.getRentPerDay());
        existingVehicle.setBranchId(vehicle.getBranchId());

        return vehicleService.saveVehicle(existingVehicle);
    }

    // Delete vehicle
    @DeleteMapping("/{id}")
    public String deleteVehicle(@PathVariable String id) {
        vehicleService.deleteVehicle(id);
        return "Vehicle deleted successfully";
    }

    // WHERE - rent greater than 1000
    @GetMapping("/above-1000")
    public List<Vehicle> getVehiclesAbove1000() {
        return vehicleService.getVehiclesAbove1000();
    }

    // LIKE - search vehicle number
    @GetMapping("/number/{number}")
    public List<Vehicle> getVehiclesByNumber(@PathVariable String number) {
        return vehicleService.getVehiclesByNumber(number + "%");
    }

    // ORDER BY - highest rent first
    @GetMapping("/sort")
    public List<Vehicle> getVehiclesOrderByRent() {
        return vehicleService.getVehiclesOrderByRent();
    }

    // GROUP BY - count vehicles by type
    @GetMapping("/count-by-type")
    public List<Object[]> countVehiclesByType() {
        return vehicleService.countVehiclesByType();
    }

    // HAVING - vehicle types with more than one vehicle
    @GetMapping("/having")
    public List<Object[]> findVehicleTypesWithMoreThanOne() {
        return vehicleService.findVehicleTypesWithMoreThanOne();
    }

    // AVG - average rent
    @GetMapping("/average")
    public Double findAverageRent() {
        return vehicleService.findAverageRent();
    }

    // MAX - vehicle with highest rent
    @GetMapping("/highest")
    public List<Vehicle> findVehicleWithHighestRent() {
        return vehicleService.findVehicleWithHighestRent();
    }

    // Subquery - above average rent
    @GetMapping("/above-average")
    public List<Vehicle> findVehiclesAboveAverage() {
        return vehicleService.findVehiclesAboveAverage();
    }

    // ANY
    @GetMapping("/greater-than-any-bike")
    public List<Vehicle> findVehiclesGreaterThanAnyBike() {
        return vehicleService.findVehiclesGreaterThanAnyBike();
    }

    // ALL
    @GetMapping("/greater-than-all-bikes")
    public List<Vehicle> findVehiclesGreaterThanAllBikes() {
        return vehicleService.findVehiclesGreaterThanAllBikes();
    }

    // EXISTS - rented vehicles
    @GetMapping("/rented")
    public List<Vehicle> findRentedVehicles() {
        return vehicleService.findRentedVehicles();
    }

    // NOT EXISTS - never rented vehicles
    @GetMapping("/never-rented")
    public List<Vehicle> findNeverRentedVehicles() {
        return vehicleService.findNeverRentedVehicles();
    }
}