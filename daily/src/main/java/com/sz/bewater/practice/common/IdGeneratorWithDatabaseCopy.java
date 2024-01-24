package com.sz.bewater.practice.common;

import org.springframework.util.ObjectUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class IdGeneratorWithDatabaseCopy {

    private static final String JDBC_URL = "jdbc:mysql://192.168.131.182:3306/sfp_monitoring";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "Zyuc@123456";

    private static final String ID_TABLE_NAME = "id_table";

    // 从数据库中获取计数器的方法
    private static Map<String, Integer> getAllCountersFromDatabase(String type, String relatedId) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            String query = "SELECT id, counter_value FROM " + ID_TABLE_NAME + " WHERE type = ? AND related_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, type);
                statement.setString(2, relatedId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    Map<String, Integer> counters = new HashMap<>();
                    while (resultSet.next()) {
                        String id = resultSet.getString("id");
                        int counterValue = resultSet.getInt("counter_value");
                        counters.put(id, counterValue);
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
    private static void updateCountersInDatabase(String type, String relatedId, Map<String, Integer> counters) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            // 清空原有数据
            String truncateQuery = "DELETE FROM " + ID_TABLE_NAME + " WHERE type = ? AND related_id = ?";
            try (PreparedStatement truncateStatement = connection.prepareStatement(truncateQuery)) {
                truncateStatement.setString(1, type);
                truncateStatement.setString(2, relatedId);
                truncateStatement.executeUpdate();
            }

            // 插入新数据
            String insertQuery = "INSERT INTO " + ID_TABLE_NAME + " (id, type, related_id, counter_value) VALUES (?, ?, ?, ?)";
            try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
                for (Map.Entry<String, Integer> entry : counters.entrySet()) {
                    String id = entry.getKey();
                    int counterValue = entry.getValue();

                    insertStatement.setString(1, id);
                    insertStatement.setString(2, type);
                    insertStatement.setString(3, relatedId);
                    insertStatement.setInt(4, counterValue);

                    insertStatement.addBatch();
                }
                insertStatement.executeBatch();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 获取下一个值
    private static int getNextValue(String type, String relatedId) {
        Map<String, Integer> counters = getAllCountersFromDatabase(type, relatedId);
        return ObjectUtils.isEmpty(counters)?1:Collections.max(counters.values());
    }

    private static void addToDatabase(String id, String type, String relatedId, int counterValue) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            String query = "INSERT INTO " + ID_TABLE_NAME + " (id, type, related_id, counter_value) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, id);
                statement.setString(2, type);
                statement.setString(3, relatedId);
                statement.setInt(4, counterValue);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static synchronized String generateUserId() {
        String userId;
        do {
            userId = generateRandomNumber(5);
        } while (isDuplicate(userId));
        addToDatabase(userId, "user_id", null, 1);
        return userId;
    }

    public static synchronized String generateServiceId(String userId) {
        int serviceIdSuffix = getNextValue("service_id", userId);
        String serviceId = userId + String.format("%03d", serviceIdSuffix);
        addToDatabase(serviceId, "service_id", userId, serviceIdSuffix+1);
        return serviceId;
    }

    public static synchronized String generateHhId(String serviceId) {
        int hhIdSuffix = getNextValue("hh_id", serviceId);
        String hhId = serviceId + String.format("%07d", hhIdSuffix);
        addToDatabase(hhId, "hh_id", serviceId, hhIdSuffix+1);
        return hhId;
    }

    public static synchronized String generateHouseId() {
        String houseId;
        do {
            houseId = generateRandomNumber(5);
        } while (isDuplicate(houseId));
        addToDatabase(houseId, "house_id", null, 1);
        return houseId;
    }

    public static synchronized String generateIpSegId(String houseId) {
        int ipSegIdSuffix = getNextValue("ip_seg_id", houseId);
        String ipSegId = houseId + String.format("%08d", ipSegIdSuffix);
        addToDatabase(ipSegId, "ip_seg_id", houseId, ipSegIdSuffix+1);
        return ipSegId;
    }

    private static boolean isDuplicate(String id) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            String query = "SELECT COUNT(*) FROM " + ID_TABLE_NAME + " WHERE id = ?";
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
        String serviceId = "93547002";
        System.out.println("Generated Service ID: " + serviceId);

        String hhId = generateHhId(serviceId);
        System.out.println("Generated HH ID: " + hhId);

//        String houseId = generateHouseId();
//        System.out.println("Generated House ID: " + houseId);
//
//        String ipSegId = generateIpSegId(houseId);
//        System.out.println("Generated IP Seg ID: " + ipSegId);
    }
}
