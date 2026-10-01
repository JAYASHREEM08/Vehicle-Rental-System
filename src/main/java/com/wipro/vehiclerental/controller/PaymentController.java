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
import com.wipro.vehiclerental.entity.Payment;
import com.wipro.vehiclerental.service.PaymentService;
 @RestController
 @RequestMapping("/api/v1/payments")
 @CrossOrigin(origins = "http://localhost:4200")
 public class PaymentController {
     private final PaymentService paymentService;
     public PaymentController(PaymentService paymentService)
      { 
        this.paymentService = paymentService; 
    }
     @GetMapping public List<Payment> getAllPayments() 
     {
         return paymentService.getAllPayments(); }
          @GetMapping("/{id}") 
          public Payment getPaymentById(@PathVariable String id)
           {
             return paymentService.getPaymentById(id) .orElseThrow(() -> new RuntimeException("Payment not found with id: " + id)); 
            }
             @PostMapping public Payment createPayment(@RequestBody Payment payment) {
                 return paymentService.savePayment(payment); 
                }
                 @PutMapping("/{id}") 
                 public Payment updatePayment(@PathVariable String id, @RequestBody Payment payment)
                  {
                     Payment existingPayment = paymentService.getPaymentById(id) .orElseThrow(() -> new RuntimeException("Payment not found with id: " + id));
                      existingPayment.setRentalId(payment.getRentalId()); 
                      existingPayment.setAmount(payment.getAmount());
                       existingPayment.setPaymentDate(payment.getPaymentDate());
                        return paymentService.savePayment(existingPayment);
                     }
                      @DeleteMapping("/{id}")
                       public String deletePayment(@PathVariable String id)
                        {
                             paymentService.deletePayment(id);
                              return "Payment deleted successfully"; 
                            }
                }