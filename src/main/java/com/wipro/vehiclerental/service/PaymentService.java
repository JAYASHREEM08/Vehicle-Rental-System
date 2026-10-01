package com.wipro.vehiclerental.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.wipro.vehiclerental.entity.Payment;
import com.wipro.vehiclerental.repository.PaymentRepository;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }


    // CREATE
    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }


    // READ ALL
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }


    // READ BY ID
    public Optional<Payment> getPaymentById(String id) {
        return paymentRepository.findById(id);
    }


    // DELETE
    public void deletePayment(String id) {
        paymentRepository.deleteById(id);
    }


    // WHERE
    public List<Payment> findPaymentsAbove3000() {
        return paymentRepository.findPaymentsAbove3000();
    }


    // ORDER BY
    public List<Payment> findPaymentsOrderByAmount() {
        return paymentRepository.findPaymentsOrderByAmount();
    }


    // GROUP BY
    public List<Object[]> totalPaymentByRental() {
        return paymentRepository.totalPaymentByRental();
    }


    // HAVING
    public List<Object[]> rentalsAbove3000() {
        return paymentRepository.rentalsAbove3000();
    }


    // SUM
    public Long getTotalPayment() {
        return paymentRepository.getTotalPayment();
    }


    // AVG
    public Double getAveragePayment() {
        return paymentRepository.getAveragePayment();
    }


    // MAX
    public List<Payment> getHighestPayment() {
        return paymentRepository.getHighestPayment();
    }


    // MIN
    public List<Payment> getLowestPayment() {
        return paymentRepository.getLowestPayment();
    }


    // SUBQUERY - ABOVE AVERAGE
    public List<Payment> getPaymentsAboveAverage() {
        return paymentRepository.getPaymentsAboveAverage();
    }


    // INNER JOIN
    public List<Object[]> rentalPaymentJoin() {
        return paymentRepository.rentalPaymentJoin();
    }
}