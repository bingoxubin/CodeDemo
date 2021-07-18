package com.bingoabin.algorithm.greedy;

/**
 * @author xubin03
 * @date 2021/5/27 2:01 上午
 */
public class SplitCandy {
	//NC130 分糖果问题  https://www.nowcoder.com/practice/76039109dd0b47e994c08d8319faa352?tpId=117&&tqId=37806&rp=1&ru=/ta/job-code-high&qru=/ta/job-code-high/question-ranking
	//样例：{1,5,4,2,6,3,4,2,1} 返回 16
	//分析：分糖果，数组中的数字代表分数，每个人至少一个糖果，如果分数比旁边的高，那么至少多一个糖果，问至少要多少个糖果
	//思路：先从左往右计算，右边比左边大，那么就+1，再从右往左边计算，左边比右边大，那么+1,最后两两比较，取多的相加
	public static void main(String[] args) {
		int[] arr = {1, 5, 4, 2, 6, 4, 4, 2, 1};
		System.out.println(candy(arr));
	}

	public static int candy(int[] arr) {
		int res = 0;
		int[] left = new int[arr.length];
		int[] right = new int[arr.length];
		left[0] = 1;
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] > arr[i - 1]) {
				left[i] = left[i - 1] + 1;
			} else {
				left[i] = 1;
			}
		}
		right[arr.length - 1] = 1;
		for (int i = arr.length - 2; i >= 0; i--) {
			if (arr[i] > arr[i + 1]) {
				right[i] = right[i + 1] + 1;
			} else {
				right[i] = 1;
			}
		}
		for(int i = 0;i<arr.length;i++){
			res += Math.max(left[i], right[i]);
		}
		return res;
	}
}
