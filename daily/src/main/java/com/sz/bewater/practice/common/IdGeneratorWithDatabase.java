package com.sz.bewater.practice.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class IdGeneratorWithDatabase {

    private static final String JDBC_URL = "jdbc:mysql://192.168.131.182:3306/sfp_monitoring";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "Zyuc@123456";

    private static final String COUNTERS_TABLE_NAME = "counters";
    private static final String USER_IDS_TABLE_NAME = "user_ids";
    private static final String HOUSE_IDS_TABLE_NAME = "house_ids";

    // 从数据库中获取计数器的方法
    private static Map<String, Map<String, Integer>> getAllCountersFromDatabase() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            String query = "SELECT counter_name, related_id, counter_value FROM " + COUNTERS_TABLE_NAME;
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    Map<String, Map<String, Integer>> counters = new HashMap<>();
                    while (resultSet.next()) {
                        String counterName = resultSet.getString("counter_name");
                        String relatedId = resultSet.getString("related_id");
                        int counterValue = resultSet.getInt("counter_value");

                        counters.computeIfAbsent(counterName, k -> new HashMap<>()).put(relatedId, counterValue);
                    }
                    return counters;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }

    // 更新数据库中的计数器
    private static void updateCountersInDatabase(Map<String, Map<String, Integer>> counters) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            // 清空原有数据
            String truncateQuery = "TRUNCATE TABLE " + COUNTERS_TABLE_NAME;
            try (PreparedStatement truncateStatement = connection.prepareStatement(truncateQuery)) {
                truncateStatement.executeUpdate();
            }

            // 插入新数据
            String insertQuery = "INSERT INTO " + COUNTERS_TABLE_NAME + " (counter_name, related_id, counter_value) VALUES (?, ?, ?)";
            try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
                for (Map.Entry<String, Map<String, Integer>> entry : counters.entrySet()) {
                    String counterName = entry.getKey();
                    Map<String, Integer> typeCounters = entry.getValue();
                    for (Map.Entry<String, Integer> counterEntry : typeCounters.entrySet()) {
                        String relatedId = counterEntry.getKey();
                        int counterValue = counterEntry.getValue();

                        insertStatement.setString(1, counterName);
                        insertStatement.setString(2, relatedId);
                        insertStatement.setInt(3, counterValue);

                        insertStatement.addBatch();
                    }
                }
                insertStatement.executeBatch();
            }
        } catch (SQLException e) {
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
    private static boolean isDuplicate(String tableName, String key, String id) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            String query = "SELECT COUNT(*) FROM " + tableName + " WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    resultSet.next();
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 添加到数据库的方法
    private static void addToDatabase(String tableName, String id) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            String query = "INSERT INTO " + tableName + " (id) VALUES (?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
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
            Map<String, Map<String, Integer>> counters = getAllCountersFromDatabase();
            Map<String, Integer> typeCounters = counters.computeIfAbsent(key, k -> new HashMap<>());
            int nextValue = getFromMapOrDefault(typeCounters, relatedId, 1);
            updateMapValue(typeCounters, relatedId, nextValue + 1);
            updateCountersInDatabase(counters);
            return nextValue;
        }
    }

    private static final IdCounter SERVICE_ID_COUNTER = new IdCounter("service_id_counter");
    private static final IdCounter HH_ID_COUNTER = new IdCounter("hh_id_counter");
    private static final IdCounter IP_SEG_ID_COUNTER = new IdCounter("ip_seg_id_counter");

    public static synchronized String generateUserId() {
        String userId;
        do {
            userId = generateRandomNumber(5);
        } while (isDuplicate(USER_IDS_TABLE_NAME, "id", userId));
        addToDatabase(USER_IDS_TABLE_NAME, userId);
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
            houseId = generateRandomNumber(5);
        } while (isDuplicate(HOUSE_IDS_TABLE_NAME, "id", houseId));
        addToDatabase(HOUSE_IDS_TABLE_NAME, houseId);
        return houseId;
    }

    public static synchronized String generateIpSegId(String houseId) {
        int ipSegIdSuffix = IP_SEG_ID_COUNTER.getNextValue(houseId);
        return houseId + String.format("%08d", ipSegIdSuffix);
    }

    private static String generateRandomNumber(int length) {
        Random random = new Random();
        StringBuilder randomNumber = new StringBuilder();
        for (int i = 0; i < length; i++) {
            randomNumber.append(random.nextInt(10));
        }
        return randomNumber.toString();
    }

    public static void main(String[] args) {
        // 示例用法
//        String userId = generateUserId();
//        System.out.println("Generated User ID: " + userId);

//        String serviceId = generateServiceId(userId);
        String serviceId = "19024001";
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
