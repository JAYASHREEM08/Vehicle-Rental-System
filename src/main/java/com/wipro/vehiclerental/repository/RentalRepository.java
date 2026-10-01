package com.wipro.vehiclerental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wipro.vehiclerental.entity.Rental;

public interface RentalRepository extends JpaRepository<Rental, String> {

    // WHERE
    @Query(value = "SELECT * FROM rental " +
                   "WHERE start_date > DATE '2026-09-10'",
           nativeQuery = true)
    List<Rental> findRentalsAfterSeptember10();


    // ORDER BY
    @Query(value = "SELECT * FROM rental " +
                   "ORDER BY start_date",
           nativeQuery = true)
    List<Rental> findRentalsOrderByDate();


    // GROUP BY
    @Query(value = "SELECT customer_id, COUNT(*) " +
                   "FROM rental " +
                   "GROUP BY customer_id",
           nativeQuery = true)
    List<Object[]> countRentalsByCustomer();


    // HAVING
    @Query(value = "SELECT customer_id, COUNT(*) " +
                   "FROM rental " +
                   "GROUP BY customer_id " +
                   "HAVING COUNT(*) > 1",
           nativeQuery = true)
    List<Object[]> findCustomersWithMultipleRentals();


    // RENTAL DURATION
    @Query(value = "SELECT rental_id, " +
                   "(return_date - start_date) AS rental_days " +
                   "FROM rental",
           nativeQuery = true)
    List<Object[]> findRentalDuration();


    // INNER JOIN
    @Query(value = "SELECT c.customer_name, " +
                   "r.rental_id, " +
                   "r.start_date, " +
                   "r.return_date " +
                   "FROM customer c " +
                   "INNER JOIN rental r " +
                   "ON c.customer_id = r.customer_id",
           nativeQuery = true)
    List<Object[]> customerRentalJoin();


    // LEFT JOIN
    @Query(value = "SELECT c.customer_name, " +
                   "r.rental_id " +
                   "FROM customer c " +
                   "LEFT JOIN rental r " +
                   "ON c.customer_id = r.customer_id",
           nativeQuery = true)
    List<Object[]> customerRentalLeftJoin();


    // RIGHT JOIN
    @Query(value = "SELECT c.customer_name, " +
                   "r.rental_id " +
                   "FROM customer c " +
                   "RIGHT JOIN rental r " +
                   "ON c.customer_id = r.customer_id",
           nativeQuery = true)
    List<Object[]> customerRentalRightJoin();


    // FULL OUTER JOIN
    @Query(value = "SELECT c.customer_name, " +
                   "r.rental_id " +
                   "FROM customer c " +
                   "FULL OUTER JOIN rental r " +
                   "ON c.customer_id = r.customer_id",
           nativeQuery = true)
    List<Object[]> customerRentalFullJoin();


    // CROSS JOIN
    @Query(value = "SELECT b.branch_name, " +
                   "v.vehicle_number " +
                   "FROM branch b " +
                   "CROSS JOIN vehicle v",
           nativeQuery = true)
    List<Object[]> cartesianProduct();


    // SUBQUERY - HIGHEST RENT VEHICLE
    @Query(value = "SELECT * FROM rental " +
                   "WHERE vehicle_id IN " +
                   "(SELECT vehicle_id FROM vehicle " +
                   "WHERE rent_per_day = " +
                   "(SELECT MAX(rent_per_day) FROM vehicle))",
           nativeQuery = true)
    List<Rental> rentalsOfHighestRentVehicle();


    // NESTED SUBQUERY
    @Query(value = "SELECT * FROM rental " +
                   "WHERE customer_id IN " +
                   "(SELECT customer_id FROM customer " +
                   "WHERE city = 'Chennai')",
           nativeQuery = true)
    List<Rental> rentalsByChennaiCustomers();


    // SUBQUERY - CARS
    @Query(value = "SELECT * FROM rental " +
                   "WHERE vehicle_id IN " +
                   "(SELECT vehicle_id FROM vehicle " +
                   "WHERE vehicle_type = 'Car')",
           nativeQuery = true)
    List<Rental> rentalsOfCars();


    // COMPLEX 5-TABLE JOIN
    @Query(value = "SELECT b.branch_name, " +
                   "v.vehicle_number, " +
                   "c.customer_name, " +
                   "r.start_date, " +
                   "r.return_date, " +
                   "p.amount " +
                   "FROM branch b " +
                   "JOIN vehicle v " +
                   "ON b.branch_id = v.branch_id " +
                   "JOIN rental r " +
                   "ON v.vehicle_id = r.vehicle_id " +
                   "JOIN customer c " +
                   "ON r.customer_id = c.customer_id " +
                   "JOIN payment p " +
                   "ON r.rental_id = p.rental_id",
           nativeQuery = true)
    List<Object[]> completeRentalDetails();
}