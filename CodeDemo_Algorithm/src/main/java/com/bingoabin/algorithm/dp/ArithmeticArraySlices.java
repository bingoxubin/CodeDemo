package com.bingoabin.algorithm.dp;

/**
 * @Author: xubin34
 * @Date: 2021/8/10 12:27 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class ArithmeticArraySlices {
	//Leetcode 413. 等差数列划分
	//示例：输入：nums = [1,2,3,4] 输出：3 解释：nums 中有三个子等差数组：[1, 2, 3]、[2, 3, 4] 和 [1,2,3,4] 自身。
	//分析：给一个数组，求出所有等差数组的子数组个数，需要连续的至少3个元素
	//思路：方式一：动态规划
	//     方式二：动态规划优化，节省空间
	public static void main(String[] args) {
		int[] arr = {1, 2, 3, 4};
		System.out.println(numberOfArithmeticSlices1(arr));
		System.out.println(numberOfArithmeticSlices2(arr));
	}

	//方式一：动态规划方式，创建动态规划数组，从第三个数开始计算，如果是等差数组，dp[i] = dp[i-1]+1  将dp[i]进行累加
	public static int numberOfArithmeticSlices1(int[] nums) {
		int len = nums.length;
		if (len < 3) return 0;
		int[] dp = new int[len];
		int res = 0;
		for (int i = 2; i < len; i++) {
			if (nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2]) {
				dp[i] = dp[i - 1] + 1;
				res += dp[i];
			}
		}
		return res;
	}

	//方式二：动态规划优化，将差值记录下来为div，从第三个数开始计算，如果第三个-第二个 == div 那么 t++ 如果不相等，那么div重新赋值最新的，最后res += t即可
	public static int numberOfArithmeticSlices2(int[] nums) {
		int len = nums.length;
		if (len < 3) return 0;
		int res = 0;
		int t = 0;
		int div = nums[1] - nums[0];
		for (int i = 2; i < len; i++) {
			if (nums[i] - nums[i - 1] == div) {
				t++;
			} else {
				div = nums[i] - nums[i - 1];
				t = 0;
			}
			res += t;
		}
		return res;
	}
}
