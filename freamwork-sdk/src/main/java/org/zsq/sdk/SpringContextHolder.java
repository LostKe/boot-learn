package org.zsq.sdk;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Slf4j
@Configuration//表示声明该类为 Spring 的配置类
//通过这样的形式可以制作sdk包，实现加载sdk包中的bean，不依赖主工程
@ComponentScan("org.zsq.sdk")//Configures component scanning directives for use with @Configuration classes.
public class SpringContextHolder implements ApplicationContextAware, DisposableBean {

    private static ApplicationContext applicationContext = null;

    public void destroy() throws Exception {

    }

    public static <T> T getBean(String name) {
        return (T) applicationContext.getBean(name);
    }

    public static <T> T getBean(Class<T> requiredType) {
        return applicationContext.getBean(requiredType);
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.info("setApplicationContext.......");
        SpringContextHolder.applicationContext = applicationContext;
    }
}
