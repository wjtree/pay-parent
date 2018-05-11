package com.app.util;

import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

/**
 * <p>功 能：日期操作工具类</p>
 * <p>版 权：Copyright (c) 2017</p>
 * <p>创建时间：2017年3月23日 下午4:03:41</p>
 * @author 王建
 * @version 1.0
 */
public class DateUtil {
    /** 日期格式    yyyy-MM-dd */
    public static final String FMT_DATE = "yyyy-MM-dd";
    /** 时间格式    yyyy-MM-dd HH:mm:ss */
    public static final String FMT_DATETIME = "yyyy-MM-dd HH:mm:ss";
    /** 短日期格式   yyyyMMdd */
    public static final String FMT_DATE_SHORT = "yyyyMMdd";
    /** 短时间格式   yyyyMMddHHmmss */
    public static final String FMT_DATETIME_SHORT = "yyyyMMddHHmmss";

    /**
     * 获取秒级时间戳
     * @return
     */
    public static String getSecondTimestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }

    /**
     * 获取毫秒级时间戳
     * @return
     */
    public static String getMillisTimestamp() {
        return Long.toString(System.currentTimeMillis());
    }

    /**
     * 获取当前日期时间
     * <p>返回示例: yyyy-MM-dd HH:mm:ss </p>
     * @return
     */
    public static String getCurrentDateTime() {
        return getCurrentDateTime(FMT_DATETIME);
    }

    /**
     * 将日期类型转换为yyyy-MM-dd HH:mm:ss 格式字符串
     * @param date
     * @return
     */
    public static String getDateTime(Date date) {
        return DateFormatUtils.format(date, FMT_DATETIME);
    }

    /**
     * 获取当前日期时间
     * @param pattern
     * @return
     */
    public static String getCurrentDateTime(String pattern) {
        return DateFormatUtils.format(new Date(), pattern);
    }

    /**
     * 获取当前日期时间后若干分钟的字符串时间
     * @param increment
     * @param pattern
     * @return
     */
    public static String getDateTimeByNow(Integer increment, String pattern) {
        return DateFormatUtils.format(DateUtils.addMinutes(new Date(), increment), pattern);
    }

    /**
     * 获取当前日期前若干月的日期
     * <p>返回示例: yyyyMMdd </p>
     * @param pattern
     * @return
     */
    public static String getBeforeMonthDate(Integer increment) {
        return DateFormatUtils.format(DateUtils.addMonths(new Date(), increment), FMT_DATE_SHORT);
    }

    /**
     * 获取当前日期前若干天的日期
     * <p>返回示例: yyyyMMdd </p>
     * @param pattern
     * @return
     */
    public static String getBeforeDayDate(Integer increment) {
        return DateFormatUtils.format(DateUtils.addDays(new Date(), increment), FMT_DATE_SHORT);
    }
}