package com.wipro.vehiclerental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wipro.vehiclerental.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, String> {

    // WHERE
    @Query(value = "SELECT * FROM payment " +
                   "WHERE amount > 3000",
           nativeQuery = true)
    List<Payment> findPaymentsAbove3000();


    // ORDER BY
    @Query(value = "SELECT * FROM payment " +
                   "ORDER BY amount DESC",
           nativeQuery = true)
    List<Payment> findPaymentsOrderByAmount();


    // GROUP BY
    @Query(value = "SELECT rental_id, SUM(amount) " +
                   "FROM payment " +
                   "GROUP BY rental_id",
           nativeQuery = true)
    List<Object[]> totalPaymentByRental();


    // HAVING
    @Query(value = "SELECT rental_id, SUM(amount) " +
                   "FROM payment " +
                   "GROUP BY rental_id " +
                   "HAVING SUM(amount) > 3000",
           nativeQuery = true)
    List<Object[]> rentalsAbove3000();


    // SUM
    @Query(value = "SELECT SUM(amount) " +
                   "FROM payment",
           nativeQuery = true)
    Long getTotalPayment();


    // AVG
    @Query(value = "SELECT AVG(amount) " +
                   "FROM payment",
           nativeQuery = true)
    Double getAveragePayment();


    // MAX
    @Query(value = "SELECT * FROM payment " +
                   "WHERE amount = " +
                   "(SELECT MAX(amount) FROM payment)",
           nativeQuery = true)
    List<Payment> getHighestPayment();


    // MIN
    @Query(value = "SELECT * FROM payment " +
                   "WHERE amount = " +
                   "(SELECT MIN(amount) FROM payment)",
           nativeQuery = true)
    List<Payment> getLowestPayment();


    // SUBQUERY - ABOVE AVERAGE
    @Query(value = "SELECT * FROM payment " +
                   "WHERE amount > " +
                   "(SELECT AVG(amount) FROM payment)",
           nativeQuery = true)
    List<Payment> getPaymentsAboveAverage();


    // INNER JOIN - RENTAL + PAYMENT
    @Query(value = "SELECT r.rental_id, " +
                   "r.customer_id, " +
                   "r.vehicle_id, " +
                   "p.payment_id, " +
                   "p.amount, " +
                   "p.payment_date " +
                   "FROM rental r " +
                   "INNER JOIN payment p " +
                   "ON r.rental_id = p.rental_id",
           nativeQuery = true)
    List<Object[]> rentalPaymentJoin();
}