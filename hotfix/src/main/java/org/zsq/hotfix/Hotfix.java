package org.zsq.hotfix;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Hotfix {

    private String tempName;

    public void doHotFix() {
        log.info("doHotFix method invoke ......");
    }
}
