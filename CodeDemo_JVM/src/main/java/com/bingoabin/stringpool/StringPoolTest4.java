package com.bingoabin.stringpool;

import java.util.Random;
import java.lang.String;
public class StringPoolTest4 {

	public static void main(String[] args) throws Exception {
		Random random = new Random(10);
		String s1 = new String(random.nextInt() + "").intern();
		String s2 = new String(random.nextInt() + "").intern();
		System.out.println(s1 == s2);
	}
}
