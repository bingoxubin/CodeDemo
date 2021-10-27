package com.bingoabin.time;

import org.apache.logging.log4j.core.util.datetime.FastDateFormat;

import java.text.SimpleDateFormat;

/**
 * @Author: xubin34
 * @Date: 2021/10/26 7:58 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class TimeUtil {
	public static void main(String[] args) throws Exception{
		TimeUtil timeReverse = new TimeUtil();
		System.out.println(timeReverse.test("2021-02-05T18:49:33.182888+08:00"));
		System.out.println(timeReverse.test("2021-02-05T18:49:33.183000+08:00"));
		System.out.println(timeReverse.test("2021-02-05T18:49:33.18+08:00"));
		System.out.println(timeReverse.test("2021-02-05T18:49:33.10+08:00"));

		System.out.println(timeReverse.test1("2021-02-05T18:49:33.182888+08:00"));
		System.out.println(timeReverse.test1("2021-02-05T18:49:33.183000+08:00"));
		System.out.println(timeReverse.test1("2021-02-05T18:49:33.18+08:00"));
		System.out.println(timeReverse.test1("2021-02-05T18:49:33.10+08:00"));
	}

	public String test(String stringTime) throws Exception{
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").parse(stringTime));
	}

	public String test1(String str1) throws Exception {
		return FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss").format(FastDateFormat.getInstance("yyyy-MM-dd'T'HH:mm:ss.SSSSSS").parse(str1));
	}
}
