package com.leviathan.shop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("Pointcuts.allGetMethodsInService()")
    public void before() {
        logger.info("Try to get information.");
    }

    @After("Pointcuts.allGetMethodsInService()")
    public void after() {
        logger.info("Successful get information.");
    }

    @AfterReturning(pointcut = "Pointcuts.allAddMethodsInService()", returning = "result")
    public void something(Object result) {
        logger.info("Successful add {} [{}].", result.getClass().getSimpleName(), result.hashCode());
    }

    @Around("Pointcuts.exceptions()")
    public Object loggingException(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        Exception exception = (Exception) proceedingJoinPoint.getArgs()[0];
        logger.info("Caught the {}: {}.", exception.getClass().getSimpleName(), exception.getMessage());
        return proceedingJoinPoint.proceed();
    }
}
