package com.zaqout.spring_tutorial.aop;

import jakarta.annotation.PostConstruct;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {

    @PostConstruct
    void init() {
    }

    //    @Around("execution(* com.zaqout.spring_tutorial.aop.services.*.*(..))")
    @Around("@annotation(com.zaqout.spring_tutorial.aop.annotation.TrackTime)") // run methods with annotation
    public void measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        joinPoint.proceed();

        long elapsed = System.currentTimeMillis() - start;
        System.out.println("⏱️ " + joinPoint.getSignature() + " executed in " + elapsed + " ms");
    }
}
