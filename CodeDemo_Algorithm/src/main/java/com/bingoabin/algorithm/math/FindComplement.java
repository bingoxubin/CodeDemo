package com.bingoabin.algorithm.math;

/**
 * @Author: xubin34
 * @Date: 2021/10/18 9:18 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class FindComplement {
	//Leetcode 476. 数字的补数
	//示例：输入：num = 5 输出：2  解释：5 的二进制表示为 101（没有前导零位），其补数为 010。所以你需要输出 2 。
	//分析：给你一个 正 整数 num ，输出它的补数。补数是对该数的二进制表示取反。
	//思路：从尾数开始计算，每次乘以2的倍数
	public static void main(String[] args) {
		FindComplement findComplement = new FindComplement();
		System.out.println(findComplement.findComplement(5));
	}

	public int findComplement(int num) {
		//最后二级制位 * 1
		int i = 1;
		//最终的结果
		int res = 0;
		while (num > 0) {
			//取得最后一位的二进制位
			int temp = num & 1;
			//res + i * 二进制位如果是1 就是 0  取反
			res += i * (temp == 1 ? 0 : 1);
			//往前进一位都要*2
			i *= 2;
			//num 无符号右移一位
			num >>>= 1;
		}
		return res;
	}
}
