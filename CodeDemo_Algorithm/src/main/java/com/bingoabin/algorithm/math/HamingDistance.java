package com.bingoabin.algorithm.math;

/**
 * @author bingoabin
 * @date 2022/7/7 9:54
 */
public class HamingDistance {
	//Leetcode 461. 汉明距离
	//示例：示例:
	//      输入: x = 1, y = 4
	//      输出: 2
	//      解释:
	//      1   (0 0 0 1)
	//      4   (0 1 0 0)
	//             ↑   ↑
	//      上面的箭头指出了对应二进制位不同的位置。
	//分析：两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
	//      给出两个整数 x 和 y，计算它们之间的汉明距离。
	//      注意：
	//      0 ≤ x, y < 231.
	//思路：
	public static void main(String[] args) {
		HamingDistance hamingDistance = new HamingDistance();
		System.out.println(hamingDistance.hammingDistance(1,4));
		System.out.println(hamingDistance.hammingDistance1(1,4));
	}

	//方式二：位运算
	public int hammingDistance1(int x, int y) {
		int count = 0;
		while (x != 0 || y != 0) {
			int a = x & 1;
			int b = y & 1;
			if (a != b) count++;
			x >>>= 1;
			y >>>= 1;
		}
		return count;
	}

	//方式一:取异或
	public int hammingDistance(int x, int y) {
		return Integer.bitCount(x ^ y);
	}
}
