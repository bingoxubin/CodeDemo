package com.bingoabin.algorithm.math;

/**
 * @Author: xubin34
 * @Date: 2021/10/2 10:38 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class NumToHex {
	//Leetcode 405. 数字转换为十六进制数
	//示例：输入:  26    输出:  "1a"
	//     输入: -1     输出:  "ffffffff"
	//分析：将十进制数转换成二进制数
	//思路：方式一：分组转换，四个二进制位为一组，进行转化
	//     方式二：
	public static void main(String[] args) {
		NumToHex numToHex = new NumToHex();
		System.out.println(numToHex.toHex1(-1));
		System.out.println(numToHex.toHex2(-1));
	}

	//方式一：分组转换，四个二进制位为一组，进行转化
	public String toHex1(int num) {
		//为0 的情况
		if (num == 0) return "0";
		StringBuilder res = new StringBuilder();
		while (num != 0) {
			//取出最后的 二进制4位
			int val = num & 15;
			//将二进制 转化为 字符   0-9的情况
			char ch = (char) (val + '0');
			//如果是 大于等于10 的情况
			if (val >= 10) ch = (char) (val - 10 + 'a');
			//将字符拼接到结果集后面，记住最后进行翻转即可
			res.append(ch);
			//将 num 右移4位
			num >>>= 4;
		}
		return res.reverse().toString();
	}

	//方式二：先将num 转化为正数，然后每次除以16 求转化为字符
	public String toHex2(int num1) {
		if (num1 == 0) return "0";
		StringBuilder res = new StringBuilder();
		long num = num1;
		//将负数转化为正数
		if (num < 0) num = (long) (Math.pow(2, 32) + num);
		while (num != 0) {
			//取16的余数
			long val = num % 16;
			//将二进制 转化为 字符   0-9的情况
			char ch = (char) (val + '0');
			//如果是 大于等于10 的情况
			if (val >= 10) ch = (char) (val - 10 + 'a');
			//将字符拼接到结果集后面，记住最后进行翻转即可
			res.append(ch);
			//忽略到最后的4位
			num /= 16;
		}
		return res.reverse().toString();
	}
}
