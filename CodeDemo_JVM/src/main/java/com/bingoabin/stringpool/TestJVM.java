package com.bingoabin.stringpool;

public class TestJVM {
	public static void main(String[] args) {
		String str = System.getProperty("str");
		if (str == null) {
			System.out.println("kkb");
		} else {
			System.out.println(str);
		}
	}
}
