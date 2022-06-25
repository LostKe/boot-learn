package com.zsq.learn;


import org.zsq.sdk.HotDeploy;
import org.zsq.sdk.SpringContextHolder;
import com.zsq.other.CommonComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@Slf4j
@ComponentScan(basePackages = "com.zsq")
public class LearnApplication {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(LearnApplication.class, args);
//        User user=context.getBean(User.class);

        User user = SpringContextHolder.getBean(User.class);
        log.warn("user→{}", user.toString());
//        new SpringApplicationBuilder().web(WebApplicationType.SERVLET).run();

        Object abc = context.getEnvironment().getSystemProperties().get("abc");
        log.info("env data is " + String.valueOf(abc));
        CommonComponent cust = context.getBean(CommonComponent.class);

        AppEnv bean = SpringContextHolder.getBean("appEnv");
        String config = bean.getConfig();
        log.info("current config:{}", config);
        cust.test();

        try {
            HotDeploy.execute("/Users/zhangshuqing/jzt-git-work/hotfix/target/hotfix-1.0-SNAPSHOT.jar");
        } catch (Exception e) {
            log.error("error", e);
        }

        Object hotfix = SpringContextHolder.getBean("hotfix");
        log.info(hotfix.toString());

        //反射调用 service 的方法
        hotfix.getClass().getDeclaredMethod("doHotFix").invoke(hotfix);




    }
}
