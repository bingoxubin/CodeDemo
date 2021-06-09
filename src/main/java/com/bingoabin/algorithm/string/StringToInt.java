package com.bingoabin.algorithm.string;

/**
 * @author xubin03
 * @date 2021/6/8 12:27 上午
 */
public class StringToInt {
	//NC100 将字符串转化为整数
	//样例：输入"123" 输出：123
	//分析：将一个字符串转为整数，要考虑字母，其他字符等，正号 负号，超过Integer.MAX_VALUE或者最小值，直接返回最小值或最大值
	//思路：定义一个flag表示正号或者负号，定义一个index表示数字开始位置，默认为1，如果前面不是-或者+，那么index = 0；然后遍历字符，如果不在0-9之间直接break，如果超过最大值或者最小值，直接返回最大值，最小值
	//     注意:还要考虑字符串前后的空格，还要考虑空字符串
	public static void main(String[] args) {
		String str = "123";
		System.out.println(atoi(str));
	}

	public static int atoi(String str) {
		int res = 0;
		char[] chars = str.trim().toCharArray();
		int index = 1;
		int flag = 1;
		if (chars.length == 0) {
			return 0;
		}
		if (chars[0] == '-') {
			flag = -1;
		} else if (chars[0] != '+') {
			index = 0;
		}
		for (int i = index; i < chars.length; i++) {
			if (chars[i] < '0' || chars[i] > '9') {
				break;
			}
			if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && chars[i] - '0' > 7)) {
				return flag == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
			}
			res = res * 10 + chars[i] - '0';
		}
		return flag * res;
	}
}
