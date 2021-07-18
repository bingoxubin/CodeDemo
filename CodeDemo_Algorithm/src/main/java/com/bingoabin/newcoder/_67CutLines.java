//剪绳子
//题目描述
// 给你一根长度为n的绳子，请把绳子剪成整数长的m段（m、n都是整数，n>1并且m>1），每段绳子的长度记为k[0],k[1],...,k[m]。请问k[0]xk[1]x...xk[m]可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
// 输入描述:
// 输入一个数n，意义见题面。（2 <= n <= 60）
// 输出描述:
// 输出答案。
// 示例1
// 输入
// 8
// 输出
// 18
package com.bingoabin.newcoder;

public class _67CutLines {
	//动态规划
	public class Solution {
		public int cutRope1(int target) {
			if (target <= 1) {
				return 0;
			}
			if (target == 2) {
				return 1;
			}
			if (target == 3) {
				return 2;
			}
			int[] result = new int[target + 1];
			result[0] = 0;
			result[1] = 1;
			result[2] = 2;
			result[3] = 3;
			for (int i = 4; i <= target; i++) {
				int sum = 0;
				for (int j = 1; j <= i / 2; j++) {
					int temp = result[j] * result[target - j];
					if (temp > sum) {
						sum = temp;
					}
				}
				result[i] = sum;
			}
			return result[target];
		}
	}

	//贪婪算法
	public class Solution2 {
		public int cutRope1(int target) {
			if (target <= 1) {
				return 0;
			}
			if (target == 2) {
				return 1;
			}
			if (target == 3) {
				return 2;
			}
			int numsofthree = target / 3;
			if (target - numsofthree * 3 == 1) {
				numsofthree = numsofthree - 1;
			}
			int numsoftwo = (target - numsofthree * 3) / 2;
			return (int) (Math.pow(3.0, numsofthree) * Math.pow(2.0, numsoftwo));
		}
	}
}
