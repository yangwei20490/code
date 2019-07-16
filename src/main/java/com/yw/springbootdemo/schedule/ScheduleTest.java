package com.yw.springbootdemo.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author yangwei
 * @date 2019/5/30 10:19
 */
@Component
public class ScheduleTest {

    private static Logger logger = LoggerFactory.getLogger(ScheduleTest.class);

    @Scheduled(fixedRateString = "30000")
    public void test() {
        logger.info("30s执行一次");
    }
}
