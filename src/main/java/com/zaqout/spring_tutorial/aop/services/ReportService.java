package com.zaqout.spring_tutorial.aop.services;

import com.zaqout.spring_tutorial.aop.annotation.TrackTime;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

    public void generateReport() throws InterruptedException {
        System.out.println("📄 Generating report...");
        Thread.sleep(1200);
        System.out.println("✅ Report generated");
    }

    @TrackTime
    public void testWithAnnot() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("With annotation method!");
    }
}
