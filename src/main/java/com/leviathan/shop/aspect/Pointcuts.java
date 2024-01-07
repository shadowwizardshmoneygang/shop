package com.leviathan.shop.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {
    @Pointcut("execution(* com.leviathan.shop.service.*.get*(..))")
    public void allGetMethodsInService() {};

    @Pointcut("execution(* com.leviathan.shop.service.*.add(..))")
    public void allAddMethodsInService() {};

    @Pointcut("execution(* com.leviathan.shop.exception.*.*(..))")
    public void exceptions() {};
}
