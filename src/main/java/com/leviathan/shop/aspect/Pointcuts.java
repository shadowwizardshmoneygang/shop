package com.leviathan.shop.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {
    @Pointcut("execution(* com.leviathan.shop.service.ClothesService.get*(..))")
    public void allGetMethodsInClothesService() {};

    @Pointcut("execution(* com.leviathan.shop.service.*.add(..))")
    public void allAddMethodsInService() {};

    @Pointcut("execution(* com.leviathan.shop.controller.*.*(..))")
    public void allNotFoundException() {};
}
