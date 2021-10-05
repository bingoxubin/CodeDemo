package com.bingoabin.algorithm.math;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xubin34
 * @Date: 2021/10/3 7:25 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class FractionToDecimal {
	//Leetcode 166. 分数到小数
	//示例：输入：numerator = 4, denominator = 333  输出："0.(012)"
	//分析：将分数转化为小数，给定两个整数，分别表示分数的分子numerator 和分母 denominator，以 字符串形式返回小数 。如果小数部分为循环小数，则将循环的部分括在括号内。如果存在多个答案，只需返回 任意一个 。对于所有给定的输入，保证 答案字符串的长度小于 104 。
	//思路：
	public static void main(String[] args) {
		FractionToDecimal fractionToDecimal = new FractionToDecimal();
		System.out.println(fractionToDecimal.fractionToDecimal(4, 333));
	}

	public String fractionToDecimal(int numerator, int denominator) {
		// 转 long 计算，防止溢出
		long a = numerator, b = denominator;
		// 如果本身能够整除，直接返回计算结果
		if (a % b == 0) return String.valueOf(a / b);
		StringBuilder res = new StringBuilder();
		// 如果其一为负数，先追加负号
		if (a * b < 0) res.append('-');
		a = Math.abs(a);
		b = Math.abs(b);
		// 计算小数点前的部分，并将余数赋值给 a
		res.append(String.valueOf(a / b) + ".");
		a %= b;
		Map<Long, Integer> map = new HashMap<>();
		while (a != 0) {
			// 记录当前余数所在答案的位置，并继续模拟除法运算
			map.put(a, res.length());
			a *= 10;
			res.append(a / b);
			a %= b;
			// 如果当前余数之前出现过，则将 [出现位置 到 当前位置] 的部分抠出来（循环小数部分）
			if (map.containsKey(a)) {
				int u = map.get(a);
				return String.format("%s(%s)", res.substring(0, u), res.substring(u));
			}
		}
		return res.toString();
	}

	//自己实现一遍
	//1.先将int型 转化为 long型
	//2.判断是否能整除，如果可以整除，直接返回结果
	//3.判断是否有负号  如果有负号  在结果前面加上负号
	//4.将数字取abs 绝对值 用正数进行计算
	//5.计算小数点前面的部分，然后将余数 并赋值给a
	//6.创建hashmap key为long(余数)，value为integer(res中的位置)
	//7.进行while循环，如果a 不为0，进行循环
	//8.将余数放到map中，(a,res.length)
	//9.a * 10 借一位
	//10.res 加上 a/b
	//11.a = a % b
	//12.如果map contains a  那么取出位置，最后格式化字符串  String.format("%s(%s)", res.substring(0, u), res.substring(u))
	public String fractionToDecimal1(int numerator, int denominator) {
		//转long 防止溢出
		long a = numerator;
		long b = denominator;
		//排除能整除的情况
		if (a % b == 0) return String.valueOf(a / b);
		//创建结果集
		StringBuilder res = new StringBuilder();
		//是否有负号 如果有负号
		if (a * b < 0) res.append("-");
		//将负数转为正数
		a = Math.abs(a);
		b = Math.abs(b);
		//计算小数点前面的部分
		res.append(String.valueOf(a / b) + ".");
		//将余数给a
		a %= b;
		//创建Hashmap key存储余数，value存储res中的位置
		HashMap<Long, Integer> map = new HashMap<>();
		while (a != 0) {
			//将余数放入到hashmap中
			map.put(a, res.length());
			//a 借 一位 * 10
			a *= 10;
			//res 加上余数
			res.append(a / b);
			//a 赋值给余数
			a %= b;
			//如果map中出现了这个余数，表示开始循环了
			if (map.containsKey(a)) {
				Integer len = map.get(a);
				//格式化res
				return String.format("%s(%s)", res.substring(0, len), res.substring(len));
			}
		}
		return res.toString();
	}
}
