package com.bingoabin.algorithm.math;

import java.util.HashSet;

import static java.lang.System.*;

/**
 * @Author: xubin34
 * @Date: 2021/11/6 2:04 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class MissingNumber {
	//Leetcode 268. 丢失的数字
	//示例：输入：nums = [9,6,4,2,3,5,7,0,1]
	//     输出：8
	//     解释：n = 9，因为有 9 个数字，所以所有的数字都在范围 [0,9] 内。8 是丢失的数字，因为它没有出现在 nums 中。
	//分析：给定一个包含 [0, n] 中 n 个数的数组 nums ，找出 [0, n] 这个范围内没有出现在数组中的那个数。
	//思路：
	public static void main(String[] args) {
		int[] arr = {9, 6, 4, 2, 3, 5, 7, 0, 1};
		MissingNumber missingNumber = new MissingNumber();
		out.println(missingNumber.missingNumber1(arr));
		out.println(missingNumber.missingNumber2(arr));
		out.println(missingNumber.missingNumber3(arr));
		out.println(missingNumber.missingNumber4(arr));
	}

	//方式一：数学法 求和作差
	public int missingNumber1(int[] nums) {
		int sum = 0;
		for (int i = 0; i <= nums.length; i++) {
			sum += i;
		}
		for (int num : nums) {
			sum -= num;
		}
		return sum;
	}

	//方式二：hashset先将nums放入set中，再从0-n加入set如果能加成功就是那个数
	public int missingNumber2(int[] nums) {
		HashSet<Integer> set = new HashSet<>();
		for (int num : nums) {
			set.add(num);
		}
		for (int i = 0; i <= nums.length; i++) {
			if (set.add(i)) {
				return i;
			}
		}
		return -1;
	}

	//方式三：nums中的数，跟0-n所有数取异或
	public int missingNumber3(int[] nums) {
		int sum = nums.length;
		for (int i = 0; i < nums.length; i++) {
			sum ^= i ^ nums[i];
		}
		return sum;
	}

	//方式四：定义一个数组n+1,然后将数组放入到对应下标位置+1，然后再遍历数组，位置为0的返回
	public int missingNumber4(int[] nums) {
		int[] arr = new int[nums.length + 1];
		for (int num : nums) {
			arr[num]++;
		}
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == 0) {
				return i;
			}
		}
		return -1;
	}
}
