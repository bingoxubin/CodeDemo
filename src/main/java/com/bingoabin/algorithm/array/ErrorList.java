package com.bingoabin.algorithm.array;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author bingoabin
 * @date 2021/7/4 0:56
 */
public class ErrorList {
	//Leetcode 645. 错误的集合
	//实例：输入：nums = [1,2,2,4]  输出：[2,3]
	//分析：集合 s 包含从 1 到 n 的整数。其中一个重复，一个缺少，第一个返回的是重复的 第二个返回的是缺失的
	//思路：方式一：
	//     方式二：
	//     方式三：
	public static void main(String[] args) {
		int[] arr = {1, 2, 2, 4};
		System.out.println(Arrays.toString(findErrorNums1(arr)));
		System.out.println(Arrays.toString(findErrorNums2(arr)));
		System.out.println(Arrays.toString(findErrorNums3(arr)));
	}

	//方式一：采用异或运算  比如 1 2 2 3   正确的 1 2 3 4
	//1.先求出数组中的所有数字异或值  1 2 2 3
	//2.求出正确的数字异或值  1 2 3 4
	//3.将上面两个异或   就能得到 2 异或 4  一个缺少的 一个重复的
	//4.将上面的值  求出一个二进制位为1 的作为标记
	//5.按照这个标记  分别对两个数组进行分开异或  就能分别求出两个数字 2 4
	//6.从数组中判断某一个数字是否存在  如果存在就是重复的  如果不存在就是缺少的   得出结果值
	public static int[] findErrorNums1(int[] nums) {
		int xor = 0;
		int xor1 = 0;
		int xor2 = 0;
		//1.先求出数组中的所有数字异或值  1 2 2 3
		for (int num : nums) {
			xor ^= num;
		}
		//2.求出正确的数字异或值  1 2 3 4
		//3.将上面两个异或   就能得到 2 异或 4  一个缺少的 一个重复的
		for (int i = 1; i <= nums.length; i++) {
			xor ^= i;
		}
		//4.将上面的值  求出一个二进制位为1 的作为标记
		int flag = xor & ~(xor - 1);
		//5.按照这个标记  分别对两个数组进行分开异或  就能分别求出两个数字 2 4
		for (int num : nums) {
			if ((num & flag) != 0) {
				xor1 ^= num;
			} else {
				xor2 ^= num;
			}
		}
		for (int i = 1; i <= nums.length; i++) {
			if ((i & flag) != 0) {
				xor1 ^= i;
			} else {
				xor2 ^= i;
			}
		}
		//6.从数组中判断某一个数字是否存在  如果存在就是重复的  如果不存在就是缺少的   得出结果值
		for (int num : nums) {
			if (num == xor1) {
				return new int[]{xor1, xor2};
			}
		}
		return new int[]{xor2, xor1};
	}

	//方式二：采用排序的方式，从下标1开始计算，如果i == i -1 就是重复的  如果i - (i-1） >1 缺少的就是 i-1下标值 + 1
	//需要注意，如果最后末尾的数字是少的 需要特殊考虑
	public static int[] findErrorNums2(int[] nums) {
		Arrays.sort(nums);
		int missing = 1;
		int repetition = -1;
		//找出重复 以及缺失的数
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] == nums[i - 1]) {
				repetition = nums[i];
			} else if (nums[i] - nums[i - 1] > 1) {
				missing = nums[i - 1] + 1;
			}
		}
		//如果末尾的数字跟长度不一样，那么缺失的数字就是最后一个  长度
		if (nums[nums.length - 1] != nums.length) {
			missing = nums.length;
		}
		return new int[]{repetition, missing};
	}

	//方式三：采用hashmap的方式  记下次数  如果次数等于2 那么就是重复的  如果缺少的  就是少的
	public static int[] findErrorNums3(int[] nums) {
		HashMap<Integer, Integer> map = new HashMap<>();
		int repetition = 0;
		int missing = 0;
		for (int num : nums) {
			map.put(num, map.getOrDefault(num, 0) + 1);
		}
		for (int i = 1; i <= nums.length; i++) {
			if (map.containsKey(i)) {
				if (map.get(i) == 2) {
					repetition = i;
				}
			} else {
				missing = i;
			}
		}
		return new int[]{repetition, missing};
	}
}
