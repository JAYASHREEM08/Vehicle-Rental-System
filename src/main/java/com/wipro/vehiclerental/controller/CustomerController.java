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

import com.wipro.vehiclerental.entity.Customer;
import com.wipro.vehiclerental.service.CustomerService;

@RestController
@RequestMapping("/api/v1/customers")
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // Get all customers
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    // Get customer by ID
    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable String id) {
        return customerService.getCustomerById(id)
                .orElseThrow(() ->
                        new RuntimeException("Customer not found with id: " + id));
    }

    // Add customer
    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }

    // Update customer
    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable String id,
                                   @RequestBody Customer customer) {

        Customer existingCustomer = customerService.getCustomerById(id)
                .orElseThrow(() ->
                        new RuntimeException("Customer not found with id: " + id));

        existingCustomer.setCustomerName(customer.getCustomerName());
        existingCustomer.setPhone(customer.getPhone());
        existingCustomer.setCity(customer.getCity());

        return customerService.saveCustomer(existingCustomer);
    }

    // Delete customer
    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable String id) {
        customerService.deleteCustomer(id);
        return "Customer deleted successfully";
    }

    // WHERE - customers by city
    @GetMapping("/city/{city}")
    public List<Customer> getCustomersByCity(@PathVariable String city) {
        return customerService.getCustomersByCity(city);
    }

    // LIKE - customers by name
    @GetMapping("/name/{name}")
    public List<Customer> getCustomersByName(@PathVariable String name) {
        return customerService.getCustomersByName("%" + name + "%");
    }

    // ORDER BY
    @GetMapping("/sort")
    public List<Customer> getCustomersOrderByName() {
        return customerService.getCustomersOrderByName();
    }

    // GROUP BY
    @GetMapping("/count-by-city")
    public List<Object[]> countCustomersByCity() {
        return customerService.countCustomersByCity();
    }

    // HAVING
    @GetMapping("/having")
    public List<Object[]> findCitiesWithMoreThanOneCustomer() {
        return customerService.findCitiesWithMoreThanOneCustomer();
    }

    // IN + Subquery
    @GetMapping("/rented")
    public List<Customer> findCustomersWhoRented() {
        return customerService.findCustomersWhoRented();
    }

    // EXISTS
    @GetMapping("/exists")
    public List<Customer> findCustomersUsingExists() {
        return customerService.findCustomersUsingExists();
    }

    // NOT EXISTS
    @GetMapping("/never-rented")
    public List<Customer> findCustomersWhoNeverRented() {
        return customerService.findCustomersWhoNeverRented();
    }
}