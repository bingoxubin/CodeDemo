package com.bingoabin.test;

import java.util.Arrays;

/**
 * @author bingoabin
 * @date 2022/10/26 18:31
 * @Description:
 */
public class Test5 {
	public static void main(String[] args) {
		Integer[] arr = {1, 2, 3, 6, 5, 7, 4, 2};
		Arrays.sort(arr, (a, b) -> b - a);
		System.out.println(Arrays.toString(arr));

		String str = "abc";
		System.out.println(str);

		StringBuffer buffer = new StringBuffer("abcdefgh");
		for(int i = 0;i<buffer.length();i++){
			buffer.setCharAt(i,(char)(buffer.charAt(i) + 1));
		}
		System.out.println(buffer.toString());

	}
}
