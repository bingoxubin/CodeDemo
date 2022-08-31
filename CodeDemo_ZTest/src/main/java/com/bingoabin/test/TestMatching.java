package com.bingoabin.test;

import java.util.regex.Pattern;

/**
 * @author bingoabin
 * @date 2022/8/29 20:56
 * @Description: 测试match
 */
public class TestMatching {
	public static void main(String[] args){
		String s1 = "C)Q";
		boolean matches1 = Pattern.compile("\\*").matcher(s1).matches();
		System.out.println(matches1);
		boolean matches = s1.matches("\\*");
		System.out.println(matches);
	}
}
