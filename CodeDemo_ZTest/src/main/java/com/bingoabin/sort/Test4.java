package com.bingoabin.sort;

import java.util.Arrays;

/**
 * @author bingoabin
 * @date 2022/11/15 9:27
 * @Description:
 */
public class Test4 {
	public static void main(String[] args){
		Test4 test = new Test4();
		int[] nums = {1, 2, 3, 4, 5, 6, 8, 7};
		test.nextPermutation(nums);
		System.out.println(Arrays.toString(nums));
	}

	public void nextPermutation(int[] nums) {
		int i = nums.length - 2;
		while(i >= 0 && nums[i] >= nums[i+1]) i--;
		if(i >= 0){
			int j = nums.length - 1;
			while(j>= 0 && nums[j]<= nums[i]) j--;
			swap(nums,i,j);
		}
		reverse(nums,i+1);
	}

	public void swap (int[] nums,int i,int j){
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

	public void reverse(int[] nums,int left){
		int right = nums.length -1;
		while(left < right){
			swap(nums,left++,right--);
		}
	}
}
