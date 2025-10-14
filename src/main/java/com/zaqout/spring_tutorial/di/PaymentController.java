package com.zaqout.spring_tutorial.di;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    PaymentServiceInterface paymentServiceInterface;

    @GetMapping("/pay/{amount}")
    void pay(@PathVariable(name = "amount") double amount) {
        paymentServiceInterface.processPayment(amount);
    }
}
