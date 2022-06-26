package org.zsq.sdk;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class SDKBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        log.info(">>>>>>SDKBeanFactoryPostProcessor  postProcessBeanFactory invoke<<<<<<<<<<");
        BeanDefinition commonComponent = beanFactory.getBeanDefinition("commonComponent");
        if (commonComponent != null) {
            MutablePropertyValues propertyValues = commonComponent.getPropertyValues();
            propertyValues.add("data", "bbb");
        }
    }
}
