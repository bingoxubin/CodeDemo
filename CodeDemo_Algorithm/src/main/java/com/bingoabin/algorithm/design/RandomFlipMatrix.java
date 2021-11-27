package com.bingoabin.algorithm.design;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @Author: xubin34
 * @Date: 2021/11/27 1:44 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class RandomFlipMatrix {
	//Leetcode 519. 随机翻转矩阵
	//示例：输入["Solution", "flip", "flip", "flip", "reset", "flip"]
	//     [[3, 1], [], [], [], [], []]
	//     输出
	//     [null, [1, 0], [2, 0], [0, 0], null, [2, 0]]
	//分析：给你一个 m x n 的二元矩阵 matrix ，且所有值被初始化为 0 。请你设计一个算法，随机选取一个满足matrix[i][j] == 0 的下标(i, j) ，并将它的值变为 1 。所有满足 matrix[i][j] == 0 的下标 (i, j) 被选取的概率应当均等。
	//     尽量最少调用内置的随机函数，并且优化时间和空间复杂度。
	//     实现 Solution 类：
	//     Solution(int m, int n) 使用二元矩阵的大小 m 和 n 初始化该对象
	//     int[] flip() 返回一个满足matrix[i][j] == 0 的随机下标 [i, j] ，并将其对应格子中的值变为 1
	//     void reset() 将矩阵中所有的值重置为 0
	//思路：
	public static void main(String[] args) {
		RandomFlipMatrix randomFlipMatrix = new RandomFlipMatrix(2, 3);
		System.out.println(Arrays.toString(randomFlipMatrix.flip()));
		System.out.println(Arrays.toString(randomFlipMatrix.flip()));
		System.out.println(Arrays.toString(randomFlipMatrix.flip()));
		System.out.println(Arrays.toString(randomFlipMatrix.flip()));
		System.out.println(Arrays.toString(randomFlipMatrix.flip()));
		System.out.println(Arrays.toString(randomFlipMatrix.flip()));
		randomFlipMatrix.reset();
		System.out.println(Arrays.toString(randomFlipMatrix.flip()));
		System.out.println(Arrays.toString(randomFlipMatrix.flip()));
		System.out.println(Arrays.toString(randomFlipMatrix.flip()));
		randomFlipMatrix.reset();
	}

	int m;
	int n;
	int cnt; //剩余的个数
	HashMap<Integer, Integer> map = new HashMap<>();
	SecureRandom random = new SecureRandom();

	public RandomFlipMatrix(int m, int n) {
		this.m = m;
		this.n = n;
		cnt = m * n;
	}

	public int[] flip() {
		//从0-cnt 产生一个随机数
		int i = random.nextInt(cnt--);
		//从map中产生这个要更新的idx，如果其中有这个随机数，那么取map中的，如果没有那么就是这个随机数
		int idx = map.getOrDefault(i, i);
		//map中放入这个随机数对应的值，放入这个cnt作为value，如果有这个
		map.put(i, map.getOrDefault(cnt, cnt));
		//根据这个拿到的下标值，生成这个int数组
		return new int[]{idx / n, idx % n};
	}

	public void reset() {
		//重置cnt
		cnt = m * n;
		//map清空
		map.clear();
	}

	//重新实现一下
	public int[] flip1() {
		int i = random.nextInt(cnt--);
		int index = map.getOrDefault(i, i);
		map.put(i, map.getOrDefault(cnt, cnt));
		return new int[]{index / n, index % n};
	}
}
