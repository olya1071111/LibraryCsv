package com.itexus.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@Aspect
public class LoggerAndCacheAspect {

    private final Logger logger = Logger.getLogger(LoggerAndCacheAspect.class.getName());

    Map<String, Object> methodCache = new HashMap<>();

    @Pointcut("execution(* com.itexus.service.impl.BookLogicImpl.*(..))")
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
        } catch (NullPointerException e) {
            logger.log(Level.INFO, "The output value of the method is null!");
        }
    }

    @Around("execution(* com.itexus.service.impl.BookLogicImpl.findByName(..))")
    public Object cacheMethod(ProceedingJoinPoint pjp) throws Throwable {
        String cacheKey = getCacheKey(pjp);
        if (methodCache.get(cacheKey) != null) {
            return methodCache.get(cacheKey);
        } else {
            Object result = pjp.proceed();
            methodCache.put(cacheKey, result);
            return result;
        }
    }

    private String getCacheKey(ProceedingJoinPoint pjp) {
        return pjp.getSignature().toString() + pjp.getTarget() + Arrays.asList(pjp.getArgs());
    }
}
