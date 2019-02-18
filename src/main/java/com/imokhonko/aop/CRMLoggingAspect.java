package com.imokhonko.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Order(1)
public class CRMLoggingAspect {

    private Logger logger = Logger.getLogger(this.getClass());

    @Before("com.imokhonko.aop.AspectPointcuts.forDaoServiceControllers()")
    public void beforeMethod(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();

        logger.info("in " + methodSignature + " with arguments: " + Arrays.toString(args));
    }

    @AfterReturning(
            pointcut = "com.imokhonko.aop.AspectPointcuts.forDaoServiceControllers()",
            returning = "result"
    )
    public void afterReturningMethod(JoinPoint joinPoint, Object result) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        logger.info("method " + methodSignature + " successfully executed, with result: " + result);
    }

}
