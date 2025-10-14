package com.zaqout.spring_tutorial.di;

public class StripePaymentImpl implements PaymentServiceInterface {

    @Override
    public void processPayment(double amount) {
        System.out.println("Stripe payment");
        System.out.println("paid " + amount);
    }
}
