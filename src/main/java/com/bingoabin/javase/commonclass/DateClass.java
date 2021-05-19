package com.bingoabin.javase.commonclass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author xubin03
 * @date 2021/5/18 10:15 上午
 */
public class DateClass {
	public static void main(String[] args) throws Exception{
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

	}
}
