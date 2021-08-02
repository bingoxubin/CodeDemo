package com.bingoabin.algorithm.sort;

import java.util.Arrays;

/**
 * @author xubin34
 * @date 2021/8/3 2:16 上午
 */
public class MinLengthNoSortArray {
	//LeetCode 581. 最短无序连续子数组
	//示例：输入：nums = [2,6,4,8,10,9,15]  输出：5  你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
	//分析：给一个乱序数组，找一个连续子数组，如果对这个连续子数组进行升序排序，那么整个数组就是有序的。找到这个最短的子数组，输出长度
	//思路：方式一：将数组进行排序，然后分别从前 后进行对比原数组，看从哪边开始不一样，那就可以求出结果值
	//     方式二：两边有序，中间无顺序，那么中间的最小值大于左边有序的最大值，中间的最大值小于右边有序的最小值
	public static void main(String[] args) {
		int[] arr = {2, 6, 4, 8, 10, 9, 15};
		System.out.println(findUnsortedSubarray1(arr));
		System.out.println(findUnsortedSubarray2(arr));
	}

	//方式一：将数组进行排序，然后分别从前 后进行对比原数组，看从哪边开始不一样，那就可以求出结果值
	public static int findUnsortedSubarray1(int[] nums) {
		if (isSorted(nums)) return 0;
		//创建一个备份数组，进行拷贝
		int[] copyNums = new int[nums.length];
		System.arraycopy(nums, 0, copyNums, 0, nums.length);
		//排序拷贝来的数组
		Arrays.sort(copyNums);
		//从左边开始统计，统计到哪边开始不一样
		int left = 0;
		while (nums[left] == copyNums[left]) left++;
		//从右边开始统计，统计到哪边开始不一样
		int right = nums.length - 1;
		while (nums[right] == copyNums[right]) right--;
		//返回最终长度，就是right - left + 1
		return right - left + 1;
	}

	//辅助：统计原数组是否已经有序
	public static boolean isSorted(int[] nums) {
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] < nums[i - 1]) {
				return false;
			}
		}
		return true;
	}

	//方式二：两边有序，中间无顺序，那么中间的最小值大于左边有序的最大值，中间的最大值小于右边有序的最小值
	//      记下无序的首尾两个点，begin end 分别表示 0 和 - 1
	//      从左到右维护一个最大值max,在进入右段之前，那么遍历到的nums[i]都是小于max的，我们要求的end就是遍历中最后一个小于max元素的位置；
	//      同理，从右到左维护一个最小值min，在进入左段之前，那么遍历到的nums[i]也都是大于min的，要求的begin也就是最后一个大于min元素的位置。
	public static int findUnsortedSubarray2(int[] nums) {
		int len = nums.length;
		int max = nums[0];
		int min = nums[len - 1];
		int begin = 0;
		int end = -1;
		for (int i = 0; i < len; i++) {
			if (max > nums[i]) {
				end = i;
			} else {
				max = nums[i];
			}

			if (min < nums[len - i - 1]) {
				begin = len - i - 1;
			} else {
				min = nums[len - i - 1];
			}
		}
		return end - begin + 1;
	}
}
