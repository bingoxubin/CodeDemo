package com.bingoabin.stringpool;

import java.security.SecureRandom;

public class StringPoolTest4 {

	public static void main(String[] args) throws Exception {
		SecureRandom random = new SecureRandom();
		String s1 = new String(random.nextInt() + "").intern();
		String s2 = new String(random.nextInt() + "").intern();
		System.out.println(s1 == s2);
	}
}
