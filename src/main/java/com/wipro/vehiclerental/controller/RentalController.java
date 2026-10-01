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

import com.wipro.vehiclerental.entity.Rental;
import com.wipro.vehiclerental.service.RentalService;

@RestController
@RequestMapping("/api/v1/rentals")
@CrossOrigin(origins = "http://localhost:4200")
public class RentalController {

    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }


    // GET ALL RENTALS
    @GetMapping
    public List<Rental> getAllRentals() {
        return rentalService.getAllRentals();
    }


    // GET RENTAL BY ID
    @GetMapping("/{id}")
    public Rental getRentalById(@PathVariable String id) {
        return rentalService.getRentalById(id)
                .orElseThrow(() ->
                        new RuntimeException("Rental not found with id: " + id));
    }


    // CREATE RENTAL
    @PostMapping
    public Rental createRental(@RequestBody Rental rental) {
        return rentalService.saveRental(rental);
    }


    // UPDATE RENTAL
    @PutMapping("/{id}")
    public Rental updateRental(@PathVariable String id,
                               @RequestBody Rental rental) {

        Rental existingRental = rentalService.getRentalById(id)
                .orElseThrow(() ->
                        new RuntimeException("Rental not found with id: " + id));

        existingRental.setCustomerId(rental.getCustomerId());
        existingRental.setVehicleId(rental.getVehicleId());
        existingRental.setStartDate(rental.getStartDate());
        existingRental.setReturnDate(rental.getReturnDate());

        return rentalService.saveRental(existingRental);
    }


    // DELETE RENTAL
    @DeleteMapping("/{id}")
    public String deleteRental(@PathVariable String id) {
        rentalService.deleteRental(id);
        return "Rental deleted successfully";
    }


    // WHERE
    @GetMapping("/after-september-10")
    public List<Rental> getRentalsAfterSeptember10() {
        return rentalService.getRentalsAfterSeptember10();
    }


    // ORDER BY
    @GetMapping("/sort")
    public List<Rental> getRentalsOrderByDate() {
        return rentalService.getRentalsOrderByDate();
    }


    // GROUP BY
    @GetMapping("/count-by-customer")
    public List<Object[]> countRentalsByCustomer() {
        return rentalService.countRentalsByCustomer();
    }


    // HAVING
    @GetMapping("/multiple")
    public List<Object[]> findCustomersWithMultipleRentals() {
        return rentalService.findCustomersWithMultipleRentals();
    }


    // RENTAL DURATION
    @GetMapping("/duration")
    public List<Object[]> findRentalDuration() {
        return rentalService.findRentalDuration();
    }


    // INNER JOIN
    @GetMapping("/join")
    public List<Object[]> customerRentalJoin() {
        return rentalService.customerRentalJoin();
    }


    // LEFT JOIN
    @GetMapping("/left-join")
    public List<Object[]> customerRentalLeftJoin() {
        return rentalService.customerRentalLeftJoin();
    }


    // RIGHT JOIN
    @GetMapping("/right-join")
    public List<Object[]> customerRentalRightJoin() {
        return rentalService.customerRentalRightJoin();
    }


    // FULL OUTER JOIN
    @GetMapping("/full-join")
    public List<Object[]> customerRentalFullJoin() {
        return rentalService.customerRentalFullJoin();
    }


    // CROSS JOIN
    @GetMapping("/cartesian")
    public List<Object[]> cartesianProduct() {
        return rentalService.cartesianProduct();
    }


    // SUBQUERY - HIGHEST RENT VEHICLE
    @GetMapping("/highest-rent-vehicle")
    public List<Rental> rentalsOfHighestRentVehicle() {
        return rentalService.rentalsOfHighestRentVehicle();
    }


    // NESTED SUBQUERY - CHENNAI CUSTOMERS
    @GetMapping("/chennai-customers")
    public List<Rental> rentalsByChennaiCustomers() {
        return rentalService.rentalsByChennaiCustomers();
    }


    // SUBQUERY - CARS
    @GetMapping("/cars")
    public List<Rental> rentalsOfCars() {
        return rentalService.rentalsOfCars();
    }


    // COMPLEX 5-TABLE JOIN
    @GetMapping("/complete")
    public List<Object[]> completeRentalDetails() {
        return rentalService.completeRentalDetails();
    }
}