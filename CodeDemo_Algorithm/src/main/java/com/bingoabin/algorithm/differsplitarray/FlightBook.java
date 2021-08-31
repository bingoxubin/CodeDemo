package com.bingoabin.algorithm.differsplitarray;

import java.util.Arrays;

/**
 * @Author: xubin34
 * @Date: 2021/8/31 10:48 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class FlightBook {
	//Leetcode 1109 航班预订统计
	//案例：输入：bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
	//     输出：[10,55,45,25,25]
	//     解释：
	//     航班编号        1   2   3   4   5
	//     预订记录 1 ：   10  10
	//     预订记录 2 ：       20  20
	//     预订记录 3 ：       25  25  25  25
	//     总座位数：      10  55  45  25  25
	//     因此，answer = [10,55,45,25,25]
	//分析：有n个航班 每一个二元数组表示，从航班第一个 到航班第二都预定了第三位的座位数，求每个航班上的预订位数
	//思路：用差分数组的思想 即可解决
	public static void main(String[] args) {
		int[][] arr = {{1, 2, 10}, {2, 3, 20}, {2, 5, 25}};
		System.out.println(Arrays.toString(corpFlightBookings(arr, 5)));
	}

	public static int[] corpFlightBookings(int[][] bookings, int n) {
		int[] res = new int[n];
		for (int[] booking : bookings) {
			res[booking[0] - 1] += booking[2];
			if (booking[1] < n) {
				res[booking[1]] -= booking[2];
			}
		}
		for (int i = 1; i < n; i++) {
			res[i] += res[i - 1];
		}
		return res;
	}
}
