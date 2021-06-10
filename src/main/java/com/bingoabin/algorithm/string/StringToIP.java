package com.bingoabin.algorithm.string;

import java.util.ArrayList;

/**
 * @author xubin03
 * @date 2021/6/10 12:43 下午
 */
public class StringToIP {
	//NC20 数字字符串转化成IP地址
	//样例：输入"25525522135" 输出：["255.255.22.135","255.255.221.35"]
	//分析：现在有一个只包含数字的字符串，将该字符串转化成IP地址的形式，返回所有可能的情况。
	//思路：回溯算法
	public static void main(String[] args) {
		String str = "25525522135";
		StringToIP stringToIP = new StringToIP();
		System.out.println(stringToIP.restoreIpAddresses(str));
	}

	ArrayList<String> list = new ArrayList<String>();

	public ArrayList<String> restoreIpAddresses(String s) {
		//用来存储四层所选择的值
		int[] segment = new int[4];
		dfs(s, 0, 0, segment);
		return list;
	}

	public void dfs(String s, int count, int start, int[] segment) {
		int sum = 0;
		//只达到4层却没有遍历完字符串，结果不成立，需要返回重新选择。
		if (count == 4) {
			//达到4层也遍历完字符串，需要的结果，加入集合。
			if (start == s.length()) {
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < count; i++) {
					sb.append(segment[i]).append(".");
				}
				//因为最后多出一个点，需要删除。
				sb.deleteCharAt(sb.length() - 1);
				list.add(sb.toString());
			}
			return;
		}
		for (int i = start; i < s.length(); i++) {
			//若这层循环开头是0，且长度大于1，那么就不符合要求，跳出循环，重新选择start.
			if (s.charAt(start) == '0') {
				if (i - start > 0) {
					break;
				}
			}
			//计算此层数的和是否符合地址规范，即小于255大于0
			sum = sum * 10 + (s.charAt(i) - '0');
			if (sum <= 255 && sum >= 0) {
				segment[count] = sum;
				dfs(s, count + 1, i + 1, segment);
			}
		}
	}
}
