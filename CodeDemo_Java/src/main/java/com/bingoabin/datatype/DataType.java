package com.bingoabin.datatype;

import org.junit.Test;

/**
 * @Author: xubin34
 * @Date: 2021/8/9 12:08 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class DataType {
	@Test
	public void test(){
		Integer a = 2000;
		Integer b = 2000;
		System.out.println(a == b);   //false
		//添加-ea
		//-Djava.lang.Integer.IntegerCache.high=3000
		System.out.println(a == b);
	}

	//装箱 拆箱
	@Test
	public void test1(){
		int num = 100;
		Integer a = Integer.valueOf(num);
		int b = a.intValue();
		System.out.println(a == b);
	}

	@Test
	public void test2(){
		Integer a = 5000;
		int b = 5000;
		System.out.println(a == b); //现将a自动拆箱  执行了a.intValue() 然后进行比较
		Integer c = 5000;
		Integer d = 5000;
		System.out.println(c == d);
	}
}
