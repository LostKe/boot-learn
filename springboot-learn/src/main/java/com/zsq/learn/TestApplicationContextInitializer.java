package com.zsq.learn;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

@Slf4j
public class TestApplicationContextInitializer implements ApplicationContextInitializer {

    public void initialize(ConfigurableApplicationContext applicationContext) {
        log.info("initialize ....");

        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        environment.getSystemProperties().put("abc", 123);
    }
}
