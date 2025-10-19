package com.zaqout.spring_tutorial.di;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication()
public class App {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(App.class, args);
        OrderService orderService = applicationContext.getBeanFactory().getBean(OrderService.class);
        orderService.pay(55.33);
    }
}
