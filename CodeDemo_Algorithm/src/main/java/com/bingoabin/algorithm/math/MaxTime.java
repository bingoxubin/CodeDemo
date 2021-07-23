package com.bingoabin.algorithm.math;

/**
 * @author xubin34
 * @date 2021/7/24 2:58 上午
 */
public class MaxTime {
	//LeetCode 1736. 替换隐藏数字得到的最晚时间
	//示例：输入：time = "2?:?0" 输出："23:50"
	//分析：给一个time字符串，有效时间是00：00 - 23：59,其中有？表示，求最大的时间
	//思路：通过if判断来进行实现
	public static void main(String[] args) {
		String time = "2?:?0";
		System.out.println(maximumTime(time));
	}

	//通过if判断来进行实现
	public static String maximumTime(String time) {
		char[] res = time.toCharArray();
		//如果前面两个都是？ 那么就是23
		if (res[0] == '?' && res[1] == '?') {
			res[0] = '2';
			res[1] = '3';
		} else if (res[0] == '?') {//如果第一个为？ 那么就判断第二个 第二个如果<= 3 那么第一个？ 就是2  否则第一个就是1
			if (res[1] <= '3') {
				res[0] = '2';
			} else {
				res[0] = '1';
			}
		} else if (res[1] == '?') {//如果第二个是？ 那么久判断第一个 第一个如果是2 那么第二个就是3  否则都是 9
			if (res[0] == '2') {
				res[1] = '3';
			} else {
				res[1] = '9';
			}
		}

		//如果第4个位置是？ 那么就是5
		if (res[3] == '?') res[3] = '5';
		//如果第5个位置是？ 那么就是9
		if (res[4] == '?') res[4] = '9';
		//return new String(res);
		return String.valueOf(res);
	}
}
