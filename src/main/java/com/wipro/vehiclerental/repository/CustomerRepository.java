package com.wipro.vehiclerental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.wipro.vehiclerental.entity.Customer;


public interface CustomerRepository extends JpaRepository<Customer, String> {

    // WHERE
    @Query(value = "SELECT * FROM customer WHERE city = :city",
           nativeQuery = true)
    List<Customer> findCustomersByCity(@Param("city") String city);

    // LIKE
    @Query(value = "SELECT * FROM customer " +
                   "WHERE customer_name LIKE :name",
           nativeQuery = true)
    List<Customer> findCustomersByName(@Param("name") String name);

    // ORDER BY
    @Query(value = "SELECT * FROM customer " +
                   "ORDER BY customer_name",
           nativeQuery = true)
    List<Customer> findCustomersOrderByName();

    // GROUP BY
    @Query(value = "SELECT city, COUNT(*) " +
                   "FROM customer GROUP BY city",
           nativeQuery = true)
    List<Object[]> countCustomersByCity();

 /*  @Query(value = "SELECT city, COUNT(*) " +
                   "FROM customer " +
                   "GROUP BY city " +
                   "HAVING COUNT(*) > 1",
           nativeQuery = true)
    List<Object[]> findCitiesWithMoreThanOneCustomer();*/

    // IN - Customers who have rentals
    @Query(value = "SELECT * FROM customer " +
                   "WHERE customer_id IN " +
                   "(SELECT customer_id FROM rental)",
           nativeQuery = true)
    List<Customer> findCustomersWhoRented();

    // EXISTS
    @Query(value = "SELECT * FROM customer c " +
                   "WHERE EXISTS " +
                   "(SELECT 1 FROM rental r " +
                   "WHERE r.customer_id = c.customer_id)",
           nativeQuery = true)
    List<Customer> findCustomersUsingExists();

    // NOT EXISTS
    @Query(value = "SELECT * FROM customer c " +
                   "WHERE NOT EXISTS " +
                   "(SELECT 1 FROM rental r " +
                   "WHERE r.customer_id = c.customer_id)",
           nativeQuery = true)
    List<Customer> findCustomersWhoNeverRented();
}