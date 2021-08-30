package com.bingoabin.algorithm.math;

/**
 * @Author: xubin34
 * @Date: 2021/8/30 10:12 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class OddLengthArraySum {
	//Leetcode 1588. 所有奇数长度子数组的和
	//示例：输入：arr = [1,4,2,5,3]
	//     输出：58
	//     解释：所有奇数长度子数组和它们的和为：
	//     [1] = 1
	//     [4] = 4
	//     [2] = 2
	//     [5] = 5
	//     [3] = 3
	//     [1,4,2] = 7
	//     [4,2,5] = 11
	//     [2,5,3] = 10
	//     [1,4,2,5,3] = 15
	//     我们将所有值求和得到 1 + 4 + 2 + 5 + 3 + 7 + 11 + 10 + 15 = 58
	//分析：给一个数组，求出所有该数组中奇数长度子数组的和 如上面示例中，长度为5的数组中，有长度为1的，为3的，为5的数组，分别求和
	//思路：方式一：前缀和,先求出前缀和，然后自外层循环计算，数组长度，从1开始，到3 到5 内层循环表示长度为l的情况下，所有可能的情况，用前缀和求值
	//     方式二：求每个数组位置累加的次数
	public static void main(String[] args) {
		int[] arr = {1, 4, 2, 5, 3};
		System.out.println(sumOddLengthSubarrays1(arr));
		System.out.println(sumOddLengthSubarrays2(arr));
		System.out.println(sumOddLengthSubarrays3(arr));
	}

	//方式一：前缀和
	public static int sumOddLengthSubarrays1(int[] arr) {
		int n = arr.length;
		int[] sum = new int[n + 1];
		for (int i = 1; i <= n; i++) sum[i] = sum[i - 1] + arr[i - 1];
		int ans = 0;
		for (int len = 1; len <= n; len += 2) {
			for (int l = 0; l + len - 1 < n; l++) {
				int r = l + len - 1;
				ans += sum[r + 1] - sum[l];
			}
		}
		return ans;
	}

	//方式二：求每个数组位置累加的次数
	public static int sumOddLengthSubarrays2(int[] arr) {
		int sum = 0;
		int n = arr.length;
		for (int i = 0; i < n; i++) {
			// left odd : (i + 1) / 2
			// right odd : (n - i) / 2
			// left even : i / 2 + 1
			// right even : (n - i - 1) / 2 + 1
			sum += arr[i] * (((i + 1) / 2) * ((n - i) / 2) + (i / 2 + 1) * ((n - i - 1) / 2 + 1));
		}
		return sum;
	}

	//方式二：求每个数组位置累加的次数
	public static int sumOddLengthSubarrays3(int[] arr) {
		int n = arr.length;
		int ans = 0;
		for (int i = 0; i < n; i++) {
			int l1 = (i + 1) / 2, r1 = (n - i) / 2; // 奇数
			int l2 = i / 2, r2 = (n - i - 1) / 2; // 偶数
			l2++;
			r2++;
			ans += (l1 * r1 + l2 * r2) * arr[i];
		}
		return ans;
	}
}
