package com.bingoabin.test;

/**
 * @Author: xubin34
 * @Date: 2021/11/17 6:33 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class TestStringBuilder {
	public static void main(String[] args) {
		StringBuilder str = new StringBuilder("safasffsafas_");
		System.out.println(str.toString().substring(0, str.length() - 1));
	}
}
