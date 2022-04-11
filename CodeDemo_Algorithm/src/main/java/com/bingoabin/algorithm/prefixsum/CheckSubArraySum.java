package com.bingoabin.algorithm.prefixsum;

import java.util.HashMap;

/**
 * @author xubin03
 * @date 2021/6/2 2:14 上午
 */
public class CheckSubArraySum {
	//Leetcode 523. 连续的子数组和 https://leetcode-cn.com/problems/continuous-subarray-sum/
	//样例：nums = [23,2,4,6,7], k = 6 输出 true  [2,4] 是一个大小为 2 的子数组，并且和为 6 。
	//分析：数组中求一个长度至少为2的子数组，满足子数组之和是k的倍数
	//思路：用前缀和的思想，将计算出来的和 求余 放到hashmap中，如果后面还有求余为该值 说明中间的和是k的倍数  比如 7 余数是1  7 1 5 余数还是1 说明1 5 之和是6的倍数
	public static void main(String[] args) {
		int[] arr = {23, 2, 4, 6, 7};
		System.out.println(checkSubarraySum(arr, 6));
	}

	public static boolean checkSubarraySum(int[] nums, int k) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		//表示取到0的时候，下标为-1  为了前缀计算，比如数组中第一个数为1，第二个数为5  那么余数是0，map中存在，此时下标为1，那么长度是 1 - （-1） = 2 满足
		map.put(0, -1);
		//前缀和
		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum = (sum + nums[i]) % k;
			if (map.containsKey(sum)) {
				//获取下标
				int index = map.get(sum);
				if (i - index >= 2) return true;
			} else {
				map.put(sum, i);
			}
		}
		return false;
	}
}
