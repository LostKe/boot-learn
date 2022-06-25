package org.zsq.sdk;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
public class SdkInitializer implements ApplicationContextInitializer {
    public void initialize(ConfigurableApplicationContext applicationContext) {
        //springIoc容器创建前执行
        log.info("SdkInitializer invoke initialize.....");
        applicationContext.getEnvironment().getSystemProperties().put("spring.banner.location", "classpath:banner.txt");
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        HotDeploy.setFactory((DefaultListableBeanFactory) beanFactory);
    }


}
