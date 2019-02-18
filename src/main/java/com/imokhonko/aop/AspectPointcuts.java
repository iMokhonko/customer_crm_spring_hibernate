package com.imokhonko.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AspectPointcuts {

    @Pointcut("execution(* com.imokhonko.controllers.*.*(..))")
    public void forControllersPackage() {}

    @Pointcut("execution(* com.imokhonko.dao.*.*(..))")
    public void forDaoPackage() {}

    @Pointcut("execution(* com.imokhonko.services.*.*(..))")
    public void forServicePackage() {}

    @Pointcut("forControllersPackage() || forDaoPackage() || forServicePackage()")
    public void forDaoServiceControllers() {}

}
