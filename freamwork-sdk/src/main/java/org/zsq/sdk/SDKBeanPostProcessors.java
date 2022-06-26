package org.zsq.sdk;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

@Component
@Slf4j
public class SDKBeanPostProcessors implements BeanPostProcessor, InitializingBean, Ordered {


    public void afterPropertiesSet() throws Exception {
        log.info(">>>>>>>>>>>SDKBeanPostProcessors init ...<<<<<<<<<<<<<<<<<<<");
    }

    //实例化、依赖注入完毕，
    //在调用显示的初始化之前完成一些定制的初始化任务

    //你再找找做                                                     wa
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        //获取bean 改变bean的值
        if (beanName.equals("commonComponent")) {

        }
        //这里不能返回null
        return bean;
    }

    //实例化、依赖注入、初始化完毕时执行
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        //获取bean 改变bean的值
        try {
            if (beanName.equals("commonComponent")) {
                Method setData = bean.getClass().getDeclaredMethod("setData", String.class);
                setData.invoke(bean, "postProcessAfterInitialization");
            }
        } catch (Exception e) {

        }

        return bean;
    }

    public int getOrder() {
        return 1000;
    }
}
