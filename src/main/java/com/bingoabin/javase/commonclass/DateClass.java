package com.bingoabin.javase.commonclass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;

/**
 * @author xubin03
 * @date 2021/5/18 10:15 上午
 */
public class DateClass {
	public static void main(String[] args) throws Exception {
		//第一代日期类 Date
		Date date = new Date();
		System.out.println(date); //Tue May 18 21:21:17 CST 2021

		//时间转字符串
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  //HH 24小时制  hh 12小时制
		System.out.println(sdf.format(date));  //2021-05-18 09:23:08

		//字符串转时间
		Date date1 = null;
		try {
			date1 = sdf.parse("2017-08-18 19:03:25");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(date1);

		//第二代日期类 Calendar
		//get
		Calendar calendar = Calendar.getInstance();
		int days = calendar.get(Calendar.DAY_OF_MONTH);
		System.out.println(days);
		//set 可变性
		calendar.set(Calendar.DAY_OF_MONTH, 22);
		System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
		//add
		calendar.add(Calendar.DAY_OF_MONTH, -4);
		System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
		//getTime  日历类->Date
		Date time = calendar.getTime();
		System.out.println(time);
		//setTime Date -> 日历类
		Date date2 = new Date();
		calendar.setTime(date2);
		System.out.println(calendar.get(Calendar.DAY_OF_MONTH));

		//第三代日期类 LocalDateTime
		//now():获取当前的日期、时间、日期+时间
		LocalDate localDate = LocalDate.now();
		LocalTime localTime = LocalTime.now();
		LocalDateTime localDateTime = LocalDateTime.now();
		System.out.println(localDate + "==" + localTime + "==" + localDateTime);
		//of():设置指定的年、月、日、时、分、秒。没有偏移量
		LocalDateTime localDateTime1 = LocalDateTime.of(2020, 10, 6, 13, 23, 43);
		System.out.println(localDateTime1);
		//getXxx()：获取相关的属性
		System.out.println(localDateTime.getDayOfMonth());
		System.out.println(localDateTime.getDayOfWeek());
		System.out.println(localDateTime.getMonth());
		System.out.println(localDateTime.getMonthValue());
		System.out.println(localDateTime.getMinute());
		//体现不可变性
		//withXxx():设置相关的属性
		LocalDate localDate1 = localDate.withDayOfMonth(22);
		System.out.println(localDate);
		System.out.println(localDate1);
		LocalDateTime localDateTime2 = localDateTime.withHour(4);
		System.out.println(localDateTime);
		System.out.println(localDateTime2);
		//不可变性
		LocalDateTime localDateTime3 = localDateTime.plusMonths(3);
		System.out.println(localDateTime);
		System.out.println(localDateTime3);
		LocalDateTime localDateTime4 = localDateTime.minusDays(6);
		System.out.println(localDateTime);
		System.out.println(localDateTime4);
	}

	/*
Instant的使用类似于 java.util.Date类
 */
	public void test2() {
		//now():获取本初子午线对应的标准时间
		Instant instant = Instant.now();
		System.out.println(instant);//2019-02-18T07:29:41.719Z

		//添加时间的偏移量
		OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));
		System.out.println(offsetDateTime);//2019-02-18T15:32:50.611+08:00

		//toEpochMilli():获取自1970年1月1日0时0分0秒（UTC）开始的毫秒数  ---> Date类的getTime()
		long milli = instant.toEpochMilli();
		System.out.println(milli);

		//ofEpochMilli():通过给定的毫秒数，获取Instant实例  -->Date(long millis)
		Instant instant1 = Instant.ofEpochMilli(1550475314878L);
		System.out.println(instant1);
	}

	/*
	DateTimeFormatter:格式化或解析日期、时间类似于SimpleDateFormat
	 */
	public void test3() {
		//方式一：预定义的标准格式。如：ISO_LOCAL_DATE_TIME;ISO_LOCAL_DATE;ISO_LOCAL_TIME
		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		//格式化:日期-->字符串
		LocalDateTime localDateTime = LocalDateTime.now();
		String str1 = formatter.format(localDateTime);
		System.out.println(localDateTime);
		System.out.println(str1);//2019-02-18T15:42:18.797

		//解析：字符串 -->日期
		TemporalAccessor parse = formatter.parse("2019-02-18T15:42:18.797");
		System.out.println(parse);

		//方式二：
		//本地化相关的格式。如：ofLocalizedDateTime()
		//FormatStyle.LONG / FormatStyle.MEDIUM / FormatStyle.SHORT :适用于LocalDateTime
		DateTimeFormatter formatter1 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
		//格式化
		String str2 = formatter1.format(localDateTime);
		System.out.println(str2);//2019年2月18日 下午03时47分16秒

		//本地化相关的格式。如：ofLocalizedDate()
		//FormatStyle.FULL / FormatStyle.LONG / FormatStyle.MEDIUM / FormatStyle.SHORT : 适用于LocalDate
		DateTimeFormatter formatter2 = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
		//格式化
		String str3 = formatter2.format(LocalDate.now());
		System.out.println(str3);//2019-2-18

		//重点： 方式三：自定义的格式。如：ofPattern(“yyyy-MM-dd hh:mm:ss”)
		DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
		//格式化
		String str4 = formatter3.format(LocalDateTime.now());
		System.out.println(str4);//2019-02-18 03:52:09

		//解析
		TemporalAccessor accessor = formatter3.parse("2019-02-18 03:52:09");
		System.out.println(accessor);
	}
}
