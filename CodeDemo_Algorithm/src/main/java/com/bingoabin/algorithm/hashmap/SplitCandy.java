package com.bingoabin.algorithm.hashmap;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @Author: xubin34
 * @Date: 2021/11/1 1:26 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class SplitCandy {
	//Leetcode 575. 分糖果
	//示例：输入: candies = [1,1,2,2,3,3] 输出: 3
	//     解析: 一共有三种种类的糖果，每一种都有两个。
	//     最优分配方案：妹妹获得[1,2,3],弟弟也获得[1,2,3]。这样使妹妹获得糖果的种类数最多。
	//分析：给一个偶数长度的数组，其中不同数字代表不同种类的糖果，每个数字代表一个糖果，要把这些糖果平均分配给弟弟妹妹，求妹妹能获得糖果的最大种类数
	//思路：方式一：hashset的方式
	//     方式二：hashset的简便写法
	//     方式三：排序
	public static void main(String[] args) {
		int[] arr = {1, 1, 2, 2, 3, 3};
		SplitCandy splitCandy = new SplitCandy();
		System.out.println(splitCandy.distributeCandies1(arr));
		System.out.println(splitCandy.distributeCandies2(arr));
		System.out.println(splitCandy.distributeCandies3(arr));
	}

	//方式一：hashset的方式，只要求得数组中所有的种类即可，
	// 如果超过数组长度的一半，那么妹妹能获得的最大种类数就是数组长度的一半，
	// 如果没有超过数组长度的一半，那么最大种类数就是种类数
	public int distributeCandies1(int[] candyType) {
		int len = candyType.length;
		HashSet<Integer> set = new HashSet<>();
		//统计同类数
		int count = 0;
		for (int candy : candyType) {
			if (set.add(candy)) {
				count++;
			}
		}
		return count > len / 2 ? len / 2 : count;
	}

	//方式二：同上思路，直接用hashset的长度计数
	public int distributeCandies2(int[] candyType) {
		HashSet<Integer> set = new HashSet<>();
		for (int candy : candyType) {
			set.add(candy);
		}
		return Math.min(set.size(), candyType.length / 2);
	}

	//方式三：排序，来统计种类数
	public int distributeCandies3(int[] candyType) {
		Arrays.sort(candyType);
		int count = 1;  //注意：count从1开始，如果下面判断第二个 比第一个大，那么实际上就有两个种类了
		for (int i = 1; i < candyType.length; i++) {
			if (candyType[i] > candyType[i - 1]) {
				count++;
			}
		}
		return Math.min(count, candyType.length / 2);
	}
}
