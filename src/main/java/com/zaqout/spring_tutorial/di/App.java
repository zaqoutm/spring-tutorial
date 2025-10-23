package com.zaqout.spring_tutorial.di;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

//@SpringBootApplication
public class App {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(App.class, args);

        // get bean
        OrderService orderService = ctx.getBeanFactory().getBean(OrderService.class);
        orderService.pay(55.33);
    }

}
