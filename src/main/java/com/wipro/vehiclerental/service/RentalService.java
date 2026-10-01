package com.wipro.vehiclerental.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.wipro.vehiclerental.entity.Rental;
import com.wipro.vehiclerental.repository.RentalRepository;

@Service
public class RentalService {

    private final RentalRepository rentalRepository;

    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    // CREATE
    public Rental saveRental(Rental rental) {
        return rentalRepository.save(rental);
    }

    // READ ALL
    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    // READ BY ID
    public Optional<Rental> getRentalById(String id) {
        return rentalRepository.findById(id);
    }

    // DELETE
    public void deleteRental(String id) {
        rentalRepository.deleteById(id);
    }


    // WHERE
    public List<Rental> getRentalsAfterSeptember10() {
        return rentalRepository.findRentalsAfterSeptember10();
    }


    // ORDER BY
    public List<Rental> getRentalsOrderByDate() {
        return rentalRepository.findRentalsOrderByDate();
    }


    // GROUP BY
    public List<Object[]> countRentalsByCustomer() {
        return rentalRepository.countRentalsByCustomer();
    }


    // HAVING
    public List<Object[]> findCustomersWithMultipleRentals() {
        return rentalRepository.findCustomersWithMultipleRentals();
    }


    // RENTAL DURATION
    public List<Object[]> findRentalDuration() {
        return rentalRepository.findRentalDuration();
    }


    // INNER JOIN
    public List<Object[]> customerRentalJoin() {
        return rentalRepository.customerRentalJoin();
    }


    // LEFT JOIN
    public List<Object[]> customerRentalLeftJoin() {
        return rentalRepository.customerRentalLeftJoin();
    }


    // RIGHT JOIN
    public List<Object[]> customerRentalRightJoin() {
        return rentalRepository.customerRentalRightJoin();
    }


    // FULL OUTER JOIN
    public List<Object[]> customerRentalFullJoin() {
        return rentalRepository.customerRentalFullJoin();
    }


    // CROSS JOIN
    public List<Object[]> cartesianProduct() {
        return rentalRepository.cartesianProduct();
    }


    // SUBQUERY - HIGHEST RENT VEHICLE
    public List<Rental> rentalsOfHighestRentVehicle() {
        return rentalRepository.rentalsOfHighestRentVehicle();
    }


    // NESTED SUBQUERY
    public List<Rental> rentalsByChennaiCustomers() {
        return rentalRepository.rentalsByChennaiCustomers();
    }


    // SUBQUERY - CARS
    public List<Rental> rentalsOfCars() {
        return rentalRepository.rentalsOfCars();
    }


    // COMPLEX 5-TABLE JOIN
    public List<Object[]> completeRentalDetails() {
        return rentalRepository.completeRentalDetails();
    }
}