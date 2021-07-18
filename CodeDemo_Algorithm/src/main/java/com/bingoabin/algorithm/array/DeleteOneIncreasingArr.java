package com.bingoabin.algorithm.array;

/**
 * @author bingoabin
 * @date 2021/6/28 1:20
 */
public class DeleteOneIncreasingArr {
	//LeetCode 5780. 删除一个元素使数组严格递增
	//样例：[1,2,10,5,7]  返回：true  并且：[2,3,1,2] 返回:false  并且：[1,1,1] 返回：false
	//分析：给一个数组  删除一个值后  是否可以保证递增数组
	//思路：常规判断
	public static void main(String[] args) {
		int[] arr = {1, 2, 10, 5, 7};

		DeleteOneIncreasingArr deleteOneIncreasingArr = new DeleteOneIncreasingArr();
		System.out.println(deleteOneIncreasingArr.canBeIncreasing(arr));
	}

	public boolean canBeIncreasing(int[] nums) {
		boolean flag = true;
		int len = nums.length;
		for (int i = 0; i < len - 1; i++) {
			if (nums[i] >= nums[i + 1]) {
				if (flag) {
					if (i + 2 >= len || nums[i + 2] > nums[i]) {
						flag = false;
					} else if (i - 1 < 0 || nums[i + 1] > nums[i - 1]) {
						flag = false;
					} else {
						return false;
					}
				} else {
					return false;
				}
			}
		}
		return true;
	}
}
