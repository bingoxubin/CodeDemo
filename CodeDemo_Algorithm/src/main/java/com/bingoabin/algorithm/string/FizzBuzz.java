package com.bingoabin.algorithm.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: xubin34
 * @Date: 2021/10/13 7:57 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class FizzBuzz {
	//Leetcode 412. Fizz Buzz
	//示例：n = 15,
	// 返回:
	// [
	//     "1",
	//     "2",
	//     "Fizz",
	//     "4",
	//     "Buzz",
	//     "Fizz",
	//     "7",
	//     "8",
	//     "Fizz",
	//     "Buzz",
	//     "11",
	//     "Fizz",
	//     "13",
	//     "14",
	//     "FizzBuzz"
	// ]
	//分析：输出从1到n的字符串表示，如果是3的倍数输出Fizz 如果是5的倍数输出Buzz 如果是3的倍数并且是5的倍数输出FizzBuzz
	//思路：方式一：循环判断
	//     方式二：map记录中间值
	public static void main(String[] args) {
		FizzBuzz fizzBuzz = new FizzBuzz();
		System.out.println(fizzBuzz.fizzBuzz1(15));
		System.out.println(fizzBuzz.fizzBuzz2(15));
	}

	//方式一：循环判断
	public List<String> fizzBuzz1(int n) {
		List<String> res = new ArrayList<>();
		for (int i = 1; i <= n; i++) {
			StringBuilder buff = new StringBuilder();
			if (i % 3 == 0) buff.append("Fizz");
			if (i % 5 == 0) buff.append("Buzz");
			if (buff.toString().equals("")) buff.append(i);
			res.add(buff.toString());
		}
		return res;
	}

	//方式二：map记录中间值
	public List<String> fizzBuzz2(int n) {
		List<String> res = new ArrayList<>();
		HashMap<Integer, String> map = new HashMap<>();
		map.put(3, "Fizz");
		map.put(5, "Buzz");
		for (int i = 1; i <= n; i++) {
			StringBuilder buff = new StringBuilder();
			for (Integer key : map.keySet()) {
				if (i % key == 0) buff.append(map.get(key));
			}
			if (buff.toString().equals("")) buff.append(i);
			res.add(buff.toString());
		}
		return res;
	}
}
