package com.bingoabin.algorithm.array;

/**
 * @author xubin03
 * @date 2021/5/17 12:47 下午
 */
public class SubArrayMaxSum {
	//NC19 子数组最大累加和问题 https://www.nowcoder.com/practice/554aa508dd5d4fefbf0f86e5fe953abd?tpId=117&tqId=37797&rp=1&ru=%2Fta%2Fjob-code-high&qru=%2Fta%2Fjob-code-high%2Fquestion-ranking&tab=answerKey
	//样例：[1, -2, 3, 5, -2, 6, -1]  返回：12  通过 3 5 -2 6可以得到最大的16
	//分析：一个数组中子数组的最大和，子数组是数组中的一部分，顺序必须一致，中间不能漏数据等，需要区分子集问题
	//思路：通过dp动态规划来求解,先定义一个结果值，对应数组中的第一个数，从第二个开始遍历，如果遍历到的数字<0,arr[i]+0，进行最大值比较
	public static void main(String[] args) {
		int[] arr = {1, -2, 3, 5, -2, 6, -1};
		System.out.println(maxsumofSubarray(arr));
	}

	public static int maxsumofSubarray(int[] arr) {
		int res = arr[0];
		for (int i = 1; i < arr.length; i++) {
			arr[i] += Math.max(arr[i - 1], 0);
			res = Math.max(res, arr[i]);
		}
		return res;
	}

	//一个求和值,sum,如果sum>0? 那么往后加，如果sum < 0?那么就0开始
	public static int maxsumofSubarray1(int[] arr) {
		int res = Integer.MIN_VALUE;
		int sum = 0;
		for (int num : arr) {
			sum = sum > 0 ? sum + num : num;
			res = Math.max(res, sum);
		}
		return res;
	}
}
