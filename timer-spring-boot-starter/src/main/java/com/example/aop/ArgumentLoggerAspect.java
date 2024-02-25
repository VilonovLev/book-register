package com.example.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Aspect
@Component
@Slf4j
public class ArgumentLoggerAspect {

    @Pointcut("@annotation(Timer)")
    public void callTimer(){}

    @Around("callTimer()")
    Object aroundTimer(ProceedingJoinPoint joinPoint) {
        Object obj;
        LocalDateTime start = LocalDateTime.now();
        try {
           obj = joinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        Duration result = Duration.between(start,LocalDateTime.now());
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().toShortString();

        log.info("{} - {} #{}",className,methodName,result.toString());
        return obj;
    }
}
