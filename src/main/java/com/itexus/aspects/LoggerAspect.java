package com.itexus.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@Aspect
public class LoggerAspect {

    private final Logger logger = Logger.getLogger(LoggerAspect.class.getName());

    @Pointcut("execution(* com.itexus.service.impl.BookLogicImpl.*(..))\"")
    public void processingServiceMethods() {
    }

    @Before("processingServiceMethods()")
    public void logMethodCall(JoinPoint jp) {
        String methodName = jp.getSignature()
                .getName();
        logger.log(Level.INFO, "Invoke method: " + methodName);
    }

    @AfterReturning(pointcut = "processingServiceMethods()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
       logger.log(Level.INFO, "Input parameters of the method: " + Arrays.toString(joinPoint.getArgs()));
       try {
           logger.log(Level.INFO, "The output value of the method: " + result.toString());
       }catch(NullPointerException e){
           logger.log(Level.INFO, "The output value of the method is null!");
       }
    }
}
