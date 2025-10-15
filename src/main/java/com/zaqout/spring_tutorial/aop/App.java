package com.zaqout.spring_tutorial.aop;

import com.zaqout.spring_tutorial.SpringTutorialApplication;
import com.zaqout.spring_tutorial.aop.services.ReportService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class App implements CommandLineRunner {
    private final ReportService reportService;
    static ConfigurableApplicationContext context;

    public App(ReportService reportService) {
        this.reportService = reportService;
    }

    public static void main(String[] args) {
        context = SpringApplication.run(SpringTutorialApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        reportService.generateReport();
        reportService.testWithAnnot();
    }
}
