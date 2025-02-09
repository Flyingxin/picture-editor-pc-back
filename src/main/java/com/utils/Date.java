package com.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Date {

    // 生成当前创建时间
    public static String getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }

    public static LocalDateTime stringToLocalDateTime(String dateTimeString){
        // 定义日期时间格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // 将字符串转换为 LocalDateTime
        return LocalDateTime.parse(dateTimeString, formatter);
    }


}
