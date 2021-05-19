package com.bingoabin.algorithm.dp;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author xubin03
 * @date 2021/5/18 12:52 下午
 */
public class MaxUpSubArray {
	//NC 91  最长递增子序列  https://www.nowcoder.com/practice/9cf027bf54714ad889d4f30ff0ae5481?tpId=117&tqId=37796&rp=1&ru=%2Fta%2Fjob-code-high&qru=%2Fta%2Fjob-code-high%2Fquestion-ranking&tab=answerKey
	//样例：[2,1,5,3,6,4,8,9,7]  输出：[1,3,4,8,9]
	//分析：给你一个数组，乱序的，求出最长上升子序列，如果有多个解，求出字典序最小的那个
	//思路：1.单单求最长上升子序列长度，方式一，采用dp动态规划方式，
	//     2.单单求最长上升子序列长度，方式一，采用dp动态规划方式，
	//     3.如果要求出具体的子序列结果，
	public static void main(String[] args) {
		int[] arr = {2, 1, 5, 3, 6, 4, 8, 9, 7};
		System.out.println(lengthOfLIS1(arr));
		System.out.println(Arrays.toString(LIS1(arr)));
	}

	//求最长上升子序列长度，方式一，动态规划方式,先标记dp[i]为1，如果nums[i] 比前面的nums[j]大，那么dp[i]修改为dp[j] + 1,复杂度n^2
	public static int lengthOfLIS1(int[] nums) {
		int[] dp = new int[nums.length];
		for (int i = 0; i < nums.length; i++) {
			dp[i] = 1;
			for (int j = 0; j < i; j++) {
				if (nums[i] > nums[j]) dp[i] = Math.max(dp[i], dp[j] + 1);
			}
		}
		System.out.println(Arrays.toString(dp));
		int max = 0;
		for (int num : dp) {
			max = Math.max(max, num);
		}
		return max;
	}

	//求最长上升子序列长度，方式二，采用arraylist的方式，不断往里面添加值，如果比最后一个都大，直接加上，如果不是，找比他大一点点的替换掉，复杂度nlogn
	public static int lengthOfLIS(int[] nums) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < nums.length; i++) {
			if (list.isEmpty() || nums[i] > list.get(list.size() - 1)) {
				list.add(nums[i]);
			} else {
				int left = 0, right = list.size() - 1;
				while (left <= right) {
					int mid = (right - left) / 2 + left;
					if (list.get(mid) < nums[i]) {
						left = mid + 1;
					} else {
						right = mid - 1;
					}
					list.set(left, nums[i]);
				}
			}
		}
		return list.size();
	}

	//求出最长上升子序列的具体值，如果有多个，求出字典序最小的,先求出dp的规划比如[2, 1, 5, 3, 6, 4, 8, 9, 7]，对应[1, 1, 2, 2, 3, 3, 4, 5, 4]，可求出结果
	public static int[] LIS(int[] arr) {
		int n = arr.length;
		// 列表的最大子序列 下标从1开始
		int[] end = new int[n + 1];
		// 存储每个元素的最大子序列个数
		int[] dp = new int[n];
		int len = 1;
		//子序列的第一个元素默认为数组第一个元素
		end[1] = arr[0];
		//第一个元素的最大子序列个数肯定是1
		dp[0] = 1;
		for (int i = 0; i < n; i++) {
			if (end[len] < arr[i]) {
				//当arr[i] > end[len] 时 arr[i]添加到 end后面
				end[++len] = arr[i];
				dp[i] = len;
			} else {
				// 当前元素小于end中的最后一个元素 利用二分法寻找第一个大于arr[i]的元素
				// end[l] 替换为当前元素 dp[]
				int l = 0;
				int r = len;
				while (l <= r) {
					int mid = (l + r) >> 1;
					if (end[mid] >= arr[i]) {
						r = mid - 1;
					} else l = mid + 1;
				}
				end[l] = arr[i];
				dp[i] = l;
			}
		}
		// System.out.println("arr:"+Arrays.toString(arr));
		// System.out.println("dp:"+Arrays.toString(dp));
		// System.out.println("end"+Arrays.toString(end));
		int[] res = new int[len];
		for (int i = n - 1; i >= 0; i--) {
			if (dp[i] == len) {
				res[--len] = arr[i];
			}
		}
		return res;
	}

	//求出最长上升子序列的具体值，如果有多个，求出字典序最小的,先求出dp的规划比如[2, 1, 5, 3, 6, 4, 8, 9, 7]，对应[1, 1, 2, 2, 3, 3, 4, 5, 4]，可求出结果
	//dp 跟原始arr一样长，目的是记录，截止到arr中的下标，所产生的上升子序列长度，从1 开始
	//end 表示比arr长一个单位，从end[1]开始，主要是存储最长上升子序列的具体序列，不是标准值，但是长度和最长上升子序列的最终长度是一致的
	public static int[] LIS1(int[] arr) {
		int[] dp = new int[arr.length];
		int[] end = new int[arr.length + 1];
		end[1] = arr[0];
		dp[0] = 1;
		int len = 1;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > end[len]) {
				end[++len] = arr[i];
				dp[i] = len;
			} else {
				int left = 0, right = len;
				while (left <= right) {
					int mid = (right - left) / 2 + left;
					if (arr[i] <= end[mid]) {
						right = mid - 1;
					} else {
						left = mid + 1;
					}
				}
				end[left] = arr[i];
				dp[i] = left;
			}
		}
		// System.out.println("arr:"+Arrays.toString(arr));
		// System.out.println("dp:"+Arrays.toString(dp));
		// System.out.println("end"+Arrays.toString(end));
		int[] res = new int[len];
		for (int i = arr.length - 1; i >= 0; i--) {
			if (dp[i] == len) {
				res[--len] = arr[i];
			}
		}
		return res;
	}
}
