package com.zaqout.spring_tutorial.di;

import org.springframework.stereotype.Service;


@Service
public class OrderService {
    private final PaymentServiceInterface paymentServiceInterface;

    public OrderService(PaymentServiceInterface paymentServiceInterface) {
        this.paymentServiceInterface = paymentServiceInterface;
    }

    void pay(double amount) {
        paymentServiceInterface.processPayment(amount);
    }
}
