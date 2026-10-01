package com.wipro.vehiclerental.service;

import com.wipro.vehiclerental.entity.Customer;
import com.wipro.vehiclerental.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // Get all customers
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Get customer by ID
    public Optional<Customer> getCustomerById(String id) {
        return customerRepository.findById(id);
    }

    // Add / Update customer
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // Delete customer
    public void deleteCustomer(String id) {
        customerRepository.deleteById(id);
    }

    // WHERE - customers by city
    public List<Customer> getCustomersByCity(String city) {
        return customerRepository.findCustomersByCity(city);
    }

    // LIKE - customers by name
    public List<Customer> getCustomersByName(String name) {
        return customerRepository.findCustomersByName(name);
    }

    // ORDER BY - customers by name
    public List<Customer> getCustomersOrderByName() {
        return customerRepository.findCustomersOrderByName();
    }

    // GROUP BY - count customers by city
    public List<Object[]> countCustomersByCity() {
        return customerRepository.countCustomersByCity();
    }

    // HAVING - cities with more than one customer
    public List<Object[]> findCitiesWithMoreThanOneCustomer() {
        return countCustomersByCity().stream()
                .filter(cityCount -> ((Number) cityCount[1]).longValue() > 1)
                .collect(java.util.stream.Collectors.toList());
    }

    // IN - customers who rented
    public List<Customer> findCustomersWhoRented() {
        return customerRepository.findCustomersWhoRented();
    }

    // EXISTS
    public List<Customer> findCustomersUsingExists() {
        return customerRepository.findCustomersUsingExists();
    }

    // NOT EXISTS
    public List<Customer> findCustomersWhoNeverRented() {
        return customerRepository.findCustomersWhoNeverRented();
    }
}