package com.leviathan.shop.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("Pointcuts.allGetMethodsInClothesService()")
    public void before() {
        logger.info("Try to get information about clothes");
    }

    @After("Pointcuts.allGetMethodsInClothesService()")
    public void after() {
        logger.info("Successful get information about clothes");
    }

    @AfterReturning(pointcut = "Pointcuts.allAddMethodsInService()", returning = "result")
    public void something(Object result) {
        logger.info("Successful add {} [{}]", result.getClass().getName(), result.hashCode());
    }
}
