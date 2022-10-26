package com.bingoabin.test;

import java.util.Scanner;

/**
 * @author bingoabin
 * @date 2022/10/26 15:14
 * @Description:
 */
public class Test4 {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		while(true){
			int year = sc.nextInt();
			System.out.println(isRun(year));
		}
	}

	public static boolean isRun(int year){
		if(year % 400 == 0){
			return true;
		}else if(year % 100 != 0 && year % 4 == 0){
			return true;
		}
		return false;
	}

	public static String getMonth(int month){
		String month1 = "";
		switch(month){
			case 1:
				month1 = "mon";
				break;
			case 2:
				month1 = "two";
				break;
			default:
				month1 = "else";
		}
		return month1;
	}
}
