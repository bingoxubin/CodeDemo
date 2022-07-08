package com.bingoabin.algorithm.math;

/**
 * @author bingoabin
 * @date 2022/7/8 13:56
 */
public class IsArmstrong {
	//Leetcode 1134. 阿姆斯特朗数
	//示例：示例 1：
	//      输入：153
	//      输出：true
	//      示例：
	//      153 是一个 3 位数，且 153 = 1^3 + 5^3 + 3^3。
	//      示例 2：
	//      输入：123
	//      输出：false
	//      解释：
	//      123 是一个 3 位数，且 123 != 1^3 + 2^3 + 3^3 = 36。
	//分析：假设存在一个 k 位数 N，其每一位上的数字的 k 次幂的总和也是 N，那么这个数是阿姆斯特朗数。
	//      给你一个正整数 N，让你来判定他是否是阿姆斯特朗数，是则返回 true，不是则返回 false。
	//思路：
	public static void main(String[] args) {
		IsArmstrong isArmstrong = new IsArmstrong();
		System.out.println(isArmstrong.isArmstrong(153));
		System.out.println(isArmstrong.isArmstrong(0));
		System.out.println(isArmstrong.isArmstrong(123));
	}

	public boolean isArmstrong(int num) {
		int sum = 0;
		int k = getNum(num);
		int res = num;
		while (num > 0) {
			sum += Math.pow(num % 10, k);
			num /= 10;
		}
		return sum == res;
	}

	public int getNum(int num) {
		int count = 0;
		while (num > 0) {
			num /= 10;
			count++;
		}
		return count;
	}
}
