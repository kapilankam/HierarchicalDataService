package com.centime.hierarchicaldataservice.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("execution(* com.centime.hierarchicaldataservice..*(..)) && @annotation(com.centime.hierarchicaldataservice.aop.MethodParams)")
    public void logMethodParams(JoinPoint point) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        Object[] args = point.getArgs();
        logger.info("Class:{}, Method: {}, Method Args {}", className, methodName, args);
    }
}
