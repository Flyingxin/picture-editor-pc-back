package com.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class GenerateString {
    public static String generateUUID(int num) {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().substring(0, num);
    }
    // 生成当前创建时间
    public static String getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }
}
