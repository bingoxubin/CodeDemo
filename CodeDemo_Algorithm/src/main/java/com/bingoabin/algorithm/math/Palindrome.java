package com.bingoabin.algorithm.math;

/**
 * @author xubin03
 * @date 2021/5/25 5:07 下午
 */
public class Palindrome {
	//NC 56 回文数  https://www.nowcoder.com/practice/35b8166c135448c5a5ba2cff8d430c32?tpId=117&tqId=37753&rp=1&ru=%2Fta%2Fjob-code-high&qru=%2Fta%2Fjob-code-high%2Fquestion-ranking&tab=answerKey
	//案例：121  输出true
	//分析：给你一个数字，判断是否是回文数，即左右对称的
	//思路：创建一个数res ，在原数的基础上从后面不断的取数字，然后加到res上，当原数变成 <= res的时候，就停止，然后比较两个数，如果想等，或者res/10 等于原数剩下的值，就是true
	public static void main(String[] args) {
		int val = 121;
		System.out.println(isPalindrome(val));
	}

	public static boolean isPalindrome(int x) {
		//如果x小于0  或者是10的倍数而且不是0  那么直接返回false
		if (x < 0 || (x % 10 == 0 && x != 0)) {
			return false;
		}
		int res = 0;
		while (x > res) {
			res = 10 * res + x % 10;
			x /= 10;
		}
		return res == x || res / 10 == x;
	}
}
