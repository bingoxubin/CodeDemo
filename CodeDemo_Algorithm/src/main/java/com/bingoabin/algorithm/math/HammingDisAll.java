package com.bingoabin.algorithm.math;

/**
 * @author xubin03
 * @date 2021/5/29 12:41 上午
 */
public class HammingDisAll {
	//LeetCode 477. 汉明距离总和 https://leetcode-cn.com/problems/total-hamming-distance/
	//案例：输入: 4, 14, 2  返回 6  总结：4和14的距离是2，4和2的距离是2，14和2的距离2  总距离是6
	// 4  00100
	//14  01110
	//2   00010
	//分析：两个整数的 汉明距离 指的是这两个数字的二进制数对应位不同的数量,计算一个数组中，任意两个数之间汉明距离的总和。
	//思路：按照每一位进行统计，比如统计第一位，都是0，那么汉明距离就是0，第二位2个1，1个0，那么这一位贡献的汉明距离就是2 * 1，  假设n个1 m个0 那么n个1碰到1个0就能贡献n,有m个0就能贡献m*n
	public static void main(String[] args) {
		int[] arr = {4, 14, 2};
		System.out.println(totalHammingDistance(arr));
	}

	public static int totalHammingDistance(int[] nums) {
		//标记每一位0和1的总个数
		int len = nums.length;
		//统计总结果
		int res = 0;
		for (int i = 0; i < 30; i++) {
			//当前位置统计1的个数
			int one = 0;
			for (int num : nums) {
				//计算当前位置是否为1，如果为1加上，统计次数
				one += (num >> i) & 1;
			}
			//最后全部加上，就是总的汉明距离
			res += one * (len - one);
		}
		return res;
	}
}
