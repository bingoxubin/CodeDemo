package com.bingoabin.test;

/**
 * @Author: xubin34
 * @Date: 2021/8/8 8:11 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class Test {
	static{
		System.out.println("init hello");
	}

	public static final String a = "hello";
	public static final int b = 2;

	public static void main(String[] args){
		System.out.println(Test.a);
		System.out.println(Test.b);
	}
}
