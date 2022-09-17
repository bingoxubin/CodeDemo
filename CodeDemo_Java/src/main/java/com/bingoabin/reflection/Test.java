package com.bingoabin.reflection;

/**
 * @author bingoabin
 * @date 2022/9/13 17:44
 * @Description: 测试
 */
public class Test {
	private String name;

	static{
		System.out.println("helloworld");
	}

	//class.forname 会编译执行static中的内容
	public static void main(String[] args) throws Exception{
		Class aClass = Class.forName("com.bingoabin.reflection.Test");
	}
}
