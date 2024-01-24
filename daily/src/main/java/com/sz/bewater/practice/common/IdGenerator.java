package com.sz.bewater.practice.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class IdGenerator {

    private static final String REDIS_HOST = "127.0.0.1";
    private static final int REDIS_PORT = 6379;
    private static final String COUNTERS_KEY = "counters";
    private static final String USER_IDS_KEY = "user_ids";
    private static final String HOUSE_IDS_KEY = "house_ids";

    // 从Redis中获取计数器的Hash Map的方法
    private static Map<String, Map<String, Integer>> getAllCountersFromRedis() {
        try (Jedis jedis = new Jedis(REDIS_HOST, REDIS_PORT)) {
            String countersJson = jedis.hget(COUNTERS_KEY, "counters");
            if (countersJson != null) {
                return parseJsonToMap(countersJson);
            }
        } catch (Exception e) {
            // Handle exceptions appropriately
            e.printStackTrace();
        }
        // 默认返回一个空Map
        return new HashMap<>();
    }

    // 将JSON字符串解析为Map
    private static Map<String, Map<String, Integer>> parseJsonToMap(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, HashMap.class);
        } catch (JsonProcessingException e) {
            // Handle exceptions appropriately
            e.printStackTrace();
        }
        // 默认返回一个空Map
        return new HashMap<>();
    }

    // 将Map转为JSON字符串
    private static String convertMapToJson(Map<String, Map<String, Integer>> counters) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(counters);
        } catch (JsonProcessingException e) {
            // Handle exceptions appropriately
            e.printStackTrace();
        }
        // 默认返回空字符串
        return "";
    }

    // 更新Redis中的计数器的Hash Map
    private static void updateCountersInRedis(Map<String, Map<String, Integer>> counters) {
        try (Jedis jedis = new Jedis(REDIS_HOST, REDIS_PORT)) {
            jedis.hset(COUNTERS_KEY, "counters", convertMapToJson(counters));
        } catch (Exception e) {
            // Handle exceptions appropriately
            e.printStackTrace();
        }
    }

    // 从Map中获取指定键的值，如果不存在则返回默认值
    private static int getFromMapOrDefault(Map<String, Integer> map, String key, int defaultValue) {
        return map.getOrDefault(key, defaultValue);
    }

    // 更新Map中指定键的值
    private static void updateMapValue(Map<String, Integer> map, String key, int value) {
        map.put(key, value);
    }

    // 检查是否重复的方法
    private static boolean isDuplicate(String key, String id) {
        try (Jedis jedis = new Jedis(REDIS_HOST, REDIS_PORT)) {
            return jedis.sismember(key, id);
        } catch (Exception e) {
            // Handle exceptions appropriately
            e.printStackTrace();
        }
        return false;
    }

    // 添加到集合的方法
    private static void addToSet(String key, String id) {
        try (Jedis jedis = new Jedis(REDIS_HOST, REDIS_PORT)) {
            jedis.sadd(key, id);
        } catch (Exception e) {
            // Handle exceptions appropriately
            e.printStackTrace();
        }
    }

    // 为每个类型的ID定义一个计数器
    private static class IdCounter {
        private final String key;

        public IdCounter(String key) {
            this.key = key;
        }

        public int getNextValue(String relatedId) {
            Map<String, Map<String, Integer>> counters = getAllCountersFromRedis();
            Map<String, Integer> typeCounters = counters.computeIfAbsent(key, k -> new HashMap<>());
            int nextValue = getFromMapOrDefault(typeCounters, relatedId, 1);
            updateMapValue(typeCounters, relatedId, nextValue + 1);
            updateCountersInRedis(counters);
            return nextValue;
        }
    }

    private static final IdCounter SERVICE_ID_COUNTER = new IdCounter("service_id_counter");
    private static final IdCounter HH_ID_COUNTER = new IdCounter("hh_id_counter");
    private static final IdCounter IP_SEG_ID_COUNTER = new IdCounter("ip_seg_id_counter");

    public static synchronized String generateUserId() {
        String userId;
        do {
            // 示例随机生成 userId，你可以根据实际需求修改
            Random random = new Random();
            userId = String.format("%05d", random.nextInt(100000));
        } while (isDuplicate(USER_IDS_KEY, userId));
        addToSet(USER_IDS_KEY, userId);
        return userId;
    }

    public static synchronized String generateServiceId(String userId) {
        int serviceIdSuffix = SERVICE_ID_COUNTER.getNextValue(userId);
        return userId + String.format("%03d", serviceIdSuffix);
    }

    public static synchronized String generateHhId(String serviceId) {
        int hhIdSuffix = HH_ID_COUNTER.getNextValue(serviceId);
        return serviceId + String.format("%07d", hhIdSuffix);
    }

    public static synchronized String generateHouseId() {
        String houseId;
        do {
            // 示例随机生成 houseId，你可以根据实际需求修改
            Random random = new Random();
            houseId = String.format("%05d", random.nextInt(100000));
        } while (isDuplicate(HOUSE_IDS_KEY, houseId));
        addToSet(HOUSE_IDS_KEY, houseId);
        return houseId;
    }

    public static synchronized String generateIpSegId(String houseId) {
        int ipSegIdSuffix = IP_SEG_ID_COUNTER.getNextValue(houseId);
        return houseId + String.format("%08d", ipSegIdSuffix);
    }

    public static void main(String[] args) {
        // 示例用法
//        String userId = generateUserId();
//        System.out.println("Generated User ID: " + userId);

//        String serviceId = generateServiceId(userId);
        String serviceId = "37740001";
//        System.out.println("Generated Service ID: " + serviceId);

        String hhId = generateHhId(serviceId);
        System.out.println("Generated HH ID: " + hhId);

//        String houseId = generateHouseId();
//        System.out.println("Generated House ID: " + houseId);
//
//        String ipSegId = generateIpSegId(houseId);
//        System.out.println("Generated IP Seg ID: " + ipSegId);
    }
}
