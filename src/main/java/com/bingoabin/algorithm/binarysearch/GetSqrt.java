package com.bingoabin.algorithm.binarysearch;

/**
 * @author xubin03
 * @date 2021/5/17 9:23 下午
 */
public class GetSqrt {
	//NC32 求平方根 https://www.nowcoder.com/practice/09fbfb16140b40499951f55113f2166c?tpId=117&tqId=37734&rp=1&ru=%2Fta%2Fjob-code-high&qru=%2Fta%2Fjob-code-high%2Fquestion-ranking&tab=answerKey
	//样例：26  返回 5
	//分析：计算所给出数据的平方根，并向下取整
	//思路：通过二分比较，不断趋近于目标值
	public static void main(String[] args) {
		System.out.println(sqrt(12));
	}

	//返回左边界
	public static int sqrt(int x) {
		int left = 1, right = x;
		while (left <= right) {
			int mid = (right - left) / 2 + left;
			if (mid == x / mid) {
				return mid;
			} else if (mid > x / mid) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return right;
	}
}
