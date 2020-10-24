package com.thoughtworks.springbootemployee.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExecutionTimeTracker {
    Logger logger = LoggerFactory.getLogger(ExecutionTimeTracker.class);

    @Around("@annotation(com.thoughtworks.springbootemployee.advice.TrackExecutionTime)")
    public Object logExecutionTime(
        ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime=System.currentTimeMillis();
        Object obj=proceedingJoinPoint.proceed();
        long endTime=System.currentTimeMillis();
        logger.info(String.format("Method name%s Time Taken to execute: %d", proceedingJoinPoint.getSignature(), endTime - startTime));
        return obj;
    }
}
