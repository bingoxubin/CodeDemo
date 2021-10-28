package com.bingoabin.algorithm.hashmap;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @Author: xubin34
 * @Date: 2021/10/28 10:23 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class ReorderedPowerOf2 {
	//Leetcode 869. 重新排序得到 2 的幂
	//示例：输入：46 输出：true
	//分析：给定正整数s，我们按任何顺序（包括原始顺序）将数字重新排序，注意其前导数字不能为零。如果我们可以通过上述方式得到2 的幂，返回 true；否则，返回 false。
	//思路：通过枚举所有的2的次幂的情况放到set中，然后遍历set中的值，是否跟n一致，比较一致的过程是通过将数字转化成10位数组统计每个数字出现的次数
	public static void main(String[] args) {
		ReorderedPowerOf2 recordPowerOf2 = new ReorderedPowerOf2();
		System.out.println(recordPowerOf2.reorderedPowerOf2(46));
	}

	public boolean reorderedPowerOf2(int n) {
		//先定义一个hashset将2的幂都放到set中
		HashSet<Integer> set = new HashSet<>();
		for (int i = 1; i < (int) 1e9 + 10; i *= 2) {
			set.add(i);
		}
		//定义一个空的10长度的数组，来存放n数字的每个数字的个数
		int[] cnts = new int[10];
		while (n != 0) {
			cnts[n % 10]++;
			n /= 10;
		}
		//然后 遍历这个set中的值，将set的值放入到一个空的arr中，统计每一位出现的次数，得到的数组跟 n统计出来的数组cnts进行对比，如果一模一样就返回true，否则的话继续遍历
		int[] cur = new int[10];
		for (int num : set) {
			// 将set 统计个数放到数组中
			Arrays.fill(cur, 0);
			while (num != 0) {
				cur[num % 10]++;
				num /= 10;
			}
			//比较两个数组是否一致
			if (Arrays.equals(cnts, cur)) {
				return true;
			}
		}
		return false;
	}
}
