package com.bingoabin.java8new;

import java.security.SecureRandom;
import java.util.stream.Stream;

/**
 * @Author: xubin34
 * @Date: 2021/8/23 3:25 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class Java8NewTest4 {
	public static void main(String[] args) {
		//测试Stream
		//创建有限元流
		Stream.of("AA", "BB", "CC").forEach(System.out::println);
		//创建无限元流
		SecureRandom random = new SecureRandom();
		Stream.generate(random::nextInt).limit(8).forEach(System.out::println);
	}
}
