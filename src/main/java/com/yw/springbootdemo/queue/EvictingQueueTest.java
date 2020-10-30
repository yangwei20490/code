package com.yw.springbootdemo.queue;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author yangwei
 * @date 2020-07-30 14:04
 */
@Component
public class EvictingQueueTest {

    protected Logger log = LoggerFactory.getLogger(getClass());

    private static final Object LOCK = new Object();

    private int maxCacheSize = 20;

    private final Map<String, VehicleDataCachePool> cachePoolMaps = Maps.newConcurrentMap();

    @Scheduled(fixedRateString = "1000")
    private void scheduledSendInfoMage() {
        Map<String, Map<String, Object>> messages = Maps.newConcurrentMap();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("vehicle", 1);
        messages.put("test1", map1);
        Map<String, Object> map2 = new HashMap<>();
        map2.put("vehicle", 2);
        messages.put("test2", map2);
        Map<String, Object> map3 = new HashMap<>();
        map3.put("vehicle", 3);
        messages.put("test2", map3);
//        Map<String, Map<String, Object>> messages = webSocketDataService.realTimeDataAll();
        if (messages.size() > 0) {
            //按车推送到缓存池
            messages.forEach((vin, record) -> {
                getCachePool(vin).pushData(vin, record);
            });
        }
    }

    public Map<String, Map<String, Object>> getAllVehicleData() {
        Map<String, Map<String, Object>> messages = Maps.newHashMap();
        this.cachePoolMaps.forEach((vin, pool) -> {
            Map<String, Object> data = pool.getData();
            if (data != null) {
                messages.put(vin, data);
            }
        });

        return messages;
    }

    private VehicleDataCachePool getCachePool(String vin) {
        VehicleDataCachePool vehicleDataCachePool = this.cachePoolMaps.get(vin);
        if (vehicleDataCachePool == null) {
            synchronized (LOCK) {
                if (vehicleDataCachePool == null) {
                    vehicleDataCachePool = new VehicleDataCachePool(vin);
                    this.cachePoolMaps.put(vin, vehicleDataCachePool);
                }
            }
        }
        return vehicleDataCachePool;
    }

    public static void main(String[] args) {
        EvictingQueueTest test = new EvictingQueueTest();
        test.scheduledSendInfoMage();

        VehicleDataCachePool vehicleDataCachePool = test.cachePoolMaps.get("test2");
        System.out.println(vehicleDataCachePool.evictingQueue.peekLast());
        test.getAllVehicleData();
    }

    private class VehicleDataCachePool {

        private final String vin;
//        private final EvictingQueue<Map<String, Object>> evictingQueue;
        private final EvictingDeque<Map<String, Object>> evictingQueue;

        private int dataFrequency;

        private AtomicBoolean canPush = new AtomicBoolean(true);
        private Map<String, Object> preMap;

        private VehicleDataCachePool(String vin) {
            this.vin = vin;

            /* Temporarily not find infomation of vehicle. */
//            Map<String, Object> vehicleBaseInfo = vehicleBaseInfoModel.getVehicleBaseInfo(vin);
//
//            Object dataFrequency = vehicleBaseInfo.get("dataFrequency");
//            if(dataFrequency != null){
//                this.dataFrequency = Integer.parseInt(dataFrequency.toString());
//            }else{
//            this.dataFrequency = 1000;
//            }

//            if (this.dataFrequency < pushRate) {
//                this.dataFrequency = pushRate;
//            }

            this.evictingQueue = EvictingDeque.create(maxCacheSize);
        }

        private void pushData(String vin, Map<String, Object> map) {
            if (isValid(vin, map)) {
                this.evictingQueue.add(map);
//                if (!this.canPush.get()) {
//                    this.canPush.set(size() >= pushThreshold);
//                }
            }
        }

        private Map<String, Object> getData() {
            Map<String, Object> poll = null;
            if (this.canPush.get()) {
                try {
                    if (!this.evictingQueue.isEmpty()) {
                        poll = this.evictingQueue.poll();
                        if (poll != null) {
                            poll.put("poolSize", size());
//                            checkNetError(poll);
                        }
                    } else {
                        this.canPush.set(false);
                    }
                } catch (Exception e) {
                    log.error(String.format("获取%s数据失败。", vin), e);
                }
            }
            return poll;
        }

        private int size() {
            return this.evictingQueue.size();
        }

        private boolean isValid(String vin, Map<String, Object> map) {
            boolean valid = true;
            //TODO 校验车辆位置信息是否有变化
//            try {
//                if (preMap != null) {
//                    //检查位置和速度
//                    String prelocation = String.format("%s,%s", preMap.get("latitude"), preMap.get("longitude"));
//                    String location = String.format("%s,%s", map.get("latitude"), map.get("longitude"));
//                    double speed = Double.parseDouble(map.getOrDefault("speed", "0").toString());
//                    valid = !(prelocation.equals(location) && speed != 0);
//
//                    if (!valid) {
//                        log.warn(String.format("车辆%s数据无效：\n (prelocation = %s, location = %s, speed = %s)",
//                                vin, prelocation, location, speed));
//
//                        long currentTime = (long) map.get("collectTime");
//                        long preTime = (long) preMap.get("collectTime");
//
//                        //统计无效数据
//                        preMap.put("validCount",
//                                (long) preMap.getOrDefault("validCount", 0L) + (currentTime - preTime));
//                        preMap.put("speed", speed);
//                    }
//                }
//
//                if (valid) {
//                    preMap = map;
//                }
//            } catch (Exception e) {
//                log.error("数据检查异常。", e);
//                valid = false;
//            }
            return valid;
        }

        private void checkNetError(Map<String, Object> current) {
            Map<String, Object> next = this.evictingQueue.peek();
            try {
                long validCount = 0;
                if (preMap != null) {
                    validCount = (long) preMap.getOrDefault("validCount", 0L);
                }
                if (next != null) {
                    //检查是否存在掉包行为
                    long currentTime = (long) current.get("collectTime");
                    long nextTime = (long) next.get("collectTime");

                    //网络延迟
                    if ((nextTime - currentTime) >= this.dataFrequency * 1.2 + validCount) {
                        current.put("netError", true);
                    }
                } else if (validCount <= 0) {
                    //避免一直传无效数据的情况
                    double speed = Double.parseDouble(current.getOrDefault("speed", "0").toString());
                    if (speed > 0) {
                        current.put("netError", true);
                    }
                }
            } catch (Exception e) {
                log.error(String.format("检查%s掉包行为失败...", vin), e);
            }
        }
    }
}
