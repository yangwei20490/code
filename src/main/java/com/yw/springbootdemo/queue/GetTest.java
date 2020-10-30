package com.yw.springbootdemo.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author yangwei
 * @date 2020-08-02 13:39
 */
@Component
public class GetTest {

    @Autowired
    private EvictingQueueTest evictingQueueTest;

//    @Scheduled(fixedRateString = "1000")
    private void get() {
        Map<String, Map<String, Object>> messages = evictingQueueTest.getAllVehicleData();
        System.out.println(messages);
    }
}
