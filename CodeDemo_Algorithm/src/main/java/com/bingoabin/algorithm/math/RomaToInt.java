package com.bingoabin.algorithm.math;

/**
 * @author xubin03
 * @date 2021/5/15 10:32 上午
 */
public class RomaToInt {
	//LeetCode.13 罗马数字转整数 https://leetcode-cn.com/problems/roman-to-integer/
	//案例：输入IV  输出4
	//分析：将roma字符串转化为阿拉伯数字，需要记住规则
	// I             1
	// V             5   IV:4
	// X             10  IX:9
	// L             50  XL:40
	// C             100  XC:90
	// D             500  CD:400
	// M             1000 CM:900
	//思路：遍历字符串，记下前一个字符代表的数字，跟后一个进行比较，如果后一个比前一个大，那就减去，否则加上
	public static void main(String[] args){
		String str = "IV";
		System.out.println(romanToInt(str));
	}

	public static int romanToInt(String s) {
		//标记结果值
		int sum = 0;
		//计算前一个数字的值
		int pre = getValue(s.charAt(0));
		//从第二个数字开始计算值
		for(int i= 1;i<s.length();i++){
			int num = getValue(s.charAt(i));
			//比较前一个数字，和当前数字，如果前面的小，那么就减去前面的值
			if(pre < num){
				sum -= pre;
			}else{
				sum += pre;
			}
			pre = num;
		}
		sum += pre;
		return sum;
	}

	public static int getValue(char ch){
		switch(ch){
			case 'I':return 1;
			case 'V':return 5;
			case 'X':return 10;
			case 'L':return 50;
			case 'C':return 100;
			case 'D':return 500;
			case 'M':return 1000;
			default:return 0;
		}
	}
}
