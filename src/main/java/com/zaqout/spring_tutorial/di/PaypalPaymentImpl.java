package com.zaqout.spring_tutorial.di;

import org.springframework.stereotype.Component;

@Component
public class PaypalPaymentImpl implements PaymentServiceInterface {

    @Override
    public void processPayment(double amount) {
        System.out.println("Paypal payment");
        System.out.println("paid " + amount);
    }
}
