package org.zsq.sdk;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;


@Slf4j

@Component
//@ConditionalOnProperty(prefix = "app.env" ,value ="config",havingValue = "test")
//根据配置来判断是否初始化这个类
//应用场景 某个调度任务本地不需要启动，其他环境需要启动则可以这样配置
@ConditionalOnExpression("#{!'local'.equals(environment.getProperty('app.env.config'))}")
public class TestBeanInit implements InitializingBean {
    public void afterPropertiesSet() throws Exception {
        log.info("TestBeanInit init ......");
    }
}
