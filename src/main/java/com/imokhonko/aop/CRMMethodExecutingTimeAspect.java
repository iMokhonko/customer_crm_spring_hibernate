package com.imokhonko.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * This aspect is used for logging method execution time for dao package.
 */
@Component
@Aspect
@Order(2)
public class CRMMethodExecutingTimeAspect {

    private Logger logger = Logger.getLogger(this.getClass());

    @Around("com.imokhonko.aop.AspectPointcuts.forDaoServiceControllers()")
    public Object methodTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object  result = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();

        long time = end - start;

        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

        logger.info("method " + methodSignature + " executed in " + time + " ms.");

        return result;
    }

}
