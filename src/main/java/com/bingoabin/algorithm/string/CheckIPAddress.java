package com.bingoabin.algorithm.string;

import java.util.Arrays;

/**
 * @author xubin03
 * @date 2021/6/10 1:59 上午
 */
public class CheckIPAddress {
	//NC113 验证IP地址
	//样例：输入"172.16.254.1" 输出：IPV4  输入"2001:0db8:85a3:0:0:8A2E:0370:7334" 输出：IPV6  输入："256.256.256.256"  输出：Neither
	//分析：给验证输入的字符串是否是有效的 IPv4 或 IPv6 地址
	//思路:用
	public static void main(String[] args) {
		String str = "172.16.254.1";
		System.out.println(solve(str));

		String[] test = "1 2  3 4 5 ".split(" ", -1);
		System.out.println(Arrays.toString(test)); //[1, 2, , 3, 4, 5, ]  如果加了-1，那么末尾是该符号的话继续切割，如果不是的话等同于split(" ");
	}

	public static String solve(String IP) {
		String[] IP4Arr = IP.split("\\.", -1); //1.1.1.1.
		if (IP4Arr.length == 4) {
			return isIP4Arr(IP4Arr);
		}

		String[] IP6Arr = IP.split(":", -1);//2001:0db8:85a3:0:0:8A2E:0370:7334:
		if (IP6Arr.length == 8) {
			return isIP6Arr(IP6Arr);
		}

		return "Neither";
	}

	public static String isIP4Arr(String[] IP4Arr) {
		for (String ip : IP4Arr) {
			if (ip.length() > 3 || ip.length() <= 0) {
				return "Neither";
			}
			for (int i = 0; i < ip.length(); ++i) {
				if (!Character.isDigit(ip.charAt(i))) {
					return "Neither";
				}
			}
			int num = Integer.parseInt(ip);
			if (num > 255 || String.valueOf(num).length() != ip.length()) {
				return "Neither";
			}
		}
		return "IPv4";
	}

	public static String isIP6Arr(String[] IP6Arr) {
		for (String ip : IP6Arr) {
			if (ip.length() > 4 || ip.length() <= 0) {
				return "Neither";
			}
			for (int i = 0; i < ip.length(); ++i) {
				char c = ip.charAt(i);
				if (!Character.isDigit(c) && !('a' <= c && c <= 'f') && !('A' <= c && c <= 'F')) {
					return "Neither";
				}
			}
		}
		return "IPv6";
	}
}
