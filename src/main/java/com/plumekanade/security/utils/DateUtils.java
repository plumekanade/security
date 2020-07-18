package com.plumekanade.security.utils;

import com.plumekanade.security.constant.ProjectConstant;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 日期工具类
 *
 * @author kanade
 * @date 2020-06-22 14:09
 */
public class DateUtils {

    //    public static SimpleDateFormat date = new SimpleDateFormat(ProjectConstant.DATE_PATTERN);
    public static SimpleDateFormat dateTime = new SimpleDateFormat(ProjectConstant.DATE_TIME_PATTERN);
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ProjectConstant.DATE_TIME_PATTERN);

    /**
     * 获取当天日期 yyyy-MM-dd
     */
    public static String nowDate() {
        return LocalDate.now().toString();
    }

    /**
     * 获取当天的时间 yyyy-MM-dd HH:mm:ss
     */
    public static String nowDateTime() {
        return LocalDateTime.now().format(formatter);
    }

    /**
     * 获取某一天减一个月(近三十天)
     */
    public static String minusMonth(String date) {
        return LocalDate.parse(date).minusMonths(1).toString();
    }

    /**
     * 加一天
     */
    public static String plusDay(String date) {
        return LocalDate.parse(date).plusDays(1).toString();
    }

    /**
     * 日期加七天
     */
    public static String plusWeek(String date) {
        return LocalDate.parse(date).plusWeeks(1).toString();
    }

    /**
     * 当前日期为起点加七天
     * 两个日期用逗号分隔作为一个字符串返回 例:
     * 2020-06-04,2020-06-11
     */
    public static String nowWeek() {
        LocalDate now = LocalDate.now();
        return now.toString() + ProjectConstant.COMMA + plusWeek(now.toString());
    }
}
