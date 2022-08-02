package com.bingoabin.utils;

import org.apache.commons.lang3.time.FastDateFormat;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public static final FastDateFormat yyyyMMdd = FastDateFormat.getInstance("yyyyMMdd");
    public static final FastDateFormat dd = FastDateFormat.getInstance("dd");
    public static final FastDateFormat yyyy_MM = FastDateFormat.getInstance("yyyy-MM");
    public static final FastDateFormat yyyy_MM_dd = FastDateFormat.getInstance("yyyy-MM-dd");
    public static final FastDateFormat yyyy_MM_dd_T = FastDateFormat.getInstance("yyyy-MM-dd'T'HH:mm:ss");
    public static final FastDateFormat DATE_TIME = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");

    /**
     * 时间戳转换成日期格式字符串
     *
     * @param seconds 精确到秒的字符串 1551926847000
     * @param
     * @return
     */
    public static String timeStamp2Date(String seconds, String format) {
        if (seconds == null || seconds.isEmpty() || "null".equals(seconds)) {
            return "";
        }
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        FastDateFormat sdf = FastDateFormat.getInstance(format);
        return sdf.format(new Date(Long.parseLong(seconds)));
    }

    public static Date timeStamp2Date(Long milliSeconds, Date defaultValue) {
        return milliSeconds == null ? defaultValue : new Date(milliSeconds);
    }

    public static long getTimestampSecond() {
        return new Date().getTime() / 1000;
    }

    public static Date getYearMonthDay() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date getDateBefore(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
        return now.getTime();
    }

    public static Date parseDt(String s, Date val) {
        if (s == null) {
            return val;
        }
        try {
            return DATE_TIME.parse(s);
        } catch (ParseException e) {
            return val;
        }
    }

    public static String toDtStr(Date d) {
        if (d == null) {
            return null;
        }
        return DATE_TIME.format(d);
    }

    public static Date parseDate(String s, Date val) {
        if (s == null) {
            return val;
        }
        try {
            return yyyy_MM_dd.parse(s);
        } catch (ParseException e) {
            return val;
        }
    }

    public static String toDateStr(Date d) {
        if (d == null) {
            return null;
        }
        return yyyy_MM_dd.format(d);
    }
}
