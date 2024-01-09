package com.leviathan.shop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {
    @Before("Pointcuts.allGetMethodsInService()")
    public void before() {
        log.info("Try to get information.");
    }

    @After("Pointcuts.allGetMethodsInService()")
    public void after() {
        log.info("Successful get information.");
    }

    @AfterReturning(pointcut = "Pointcuts.allAddMethodsInService()", returning = "result")
    public void something(Object result) {
        log.info("Successful add {} [{}].", result.getClass().getSimpleName(), result.hashCode());
    }

    @Around("Pointcuts.exceptions()")
    public Object loggingException(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        Exception exception = (Exception) proceedingJoinPoint.getArgs()[0];
        log.info("Caught the {}: {}.", exception.getClass().getSimpleName(), exception.getMessage());
        return proceedingJoinPoint.proceed();
    }
}
