package com.example;


import com.example.aop.ArgumentLoggerAspect;
import com.example.aop.RecoverExceptionAspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "timer",name = "enable")
public class AopAutoConfiguration {

    @Bean
    ArgumentLoggerAspect argumentLoggerAspect() {
        return new ArgumentLoggerAspect();
    }

    @Bean
    RecoverExceptionAspect recoverExceptionAspect() {
        return new RecoverExceptionAspect();
    }
}
