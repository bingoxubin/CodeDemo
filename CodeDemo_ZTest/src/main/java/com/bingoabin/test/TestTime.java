package com.bingoabin.test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author bingoabin
 * @date 2022/9/5 9:35
 * @Description: 测试时间日期类型
 */
public class TestTime {
	public static void main(String[] args){
		System.out.println(LocalDateTime.now());
		System.out.println(LocalDate.now());
		System.out.println(LocalTime.now());

		System.out.println(LocalTime.MIN);
		System.out.println(LocalTime.MAX);

		System.out.println(LocalTime.parse(LocalTime.MIN.toString()));
		System.out.println(LocalTime.parse(LocalTime.MAX.toString()));
	}
}
