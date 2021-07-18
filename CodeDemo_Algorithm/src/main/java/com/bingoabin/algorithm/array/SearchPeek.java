package com.bingoabin.algorithm.array;

/**
 * @author xubin03
 * @date 2021/5/26 7:41 下午
 */
public class SearchPeek {
	//NC107 寻找峰值  https://www.nowcoder.com/practice/1af528f68adc4c20bf5d1456eddb080a?tpId=117&&tqId=37831&rp=1&ru=/ta/job-code-high&qru=/ta/job-code-high/question-ranking
	//样例：{2, 4, 1, 2, 7, 8, 4}  返回 5
	//分析：给一个数组，如果这个数组的值大于左边，并且大于右边，那么就认为这个下标是山峰，求最大下标的山峰
	//思路：遍历数组，如果大于左边，大于右边就说明是山峰，需要区别临界点，比如>左边的时候，如果0话，没有左边
	public static void main(String[] args) {
		int[] arr = {2, 4, 1, 2, 7, 8, 4};
		System.out.println(solve(arr));
	}

	public static int solve(int[] a) {
		int index = 0;
		for (int i = 0; i < a.length; i++) {
			if ((i == 0 || a[i] > a[i - 1]) && (i == a.length - 1 || a[i] > a[i + 1])) {
				index = Math.max(index, i);
			}
		}
		return index;
	}
}
