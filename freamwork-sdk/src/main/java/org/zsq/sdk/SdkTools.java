package org.zsq.sdk;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;


@ConditionalOnMissingBean(value = SdkTools.class)
@Component
@Slf4j
public class SdkTools implements InitializingBean {
    public void afterPropertiesSet() throws Exception {
        log.info("skdTools init ......");
    }

}
