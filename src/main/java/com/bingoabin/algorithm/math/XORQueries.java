package com.bingoabin.algorithm.math;

import java.util.Arrays;

/**
 * @author xubin03
 * @date 2021/5/12 3:32 下午
 */
public class XORQueries {
	//LeetCode 1310. 子数组异或查询 https://leetcode-cn.com/problems/xor-queries-of-a-subarray/
	//案例：输入arr = [1,3,4,8], queries = [[0,1],[1,2],[0,3],[3,3]]  返回
	// [0,1] = 1 xor 3 = 2
	// [1,2] = 3 xor 4 = 7
	// [0,3] = 1 xor 3 xor 4 xor 8 = 14
	// [3,3] = 8
	//分析：给一个arr数组，然后queries中的数组表示，从0-1的arr下标异或值
	//思路：求出一个异或前缀数组，然后求值，举例：[1,3,4,8] 求出前缀和为[0,1,2,6,14],求0-1就是求0 ^ 2,求0-3就是位置求0^4
	public static void main(String[] args){
		int[] arr = {1, 3, 4, 8};
		int[][] queries = {{0,1},{1,2},{0,3},{3,3}};
		System.out.println(Arrays.toString(xorQueries(arr, queries)));
	}

	public static int[] xorQueries(int[] arr, int[][] queries) {
		int len = arr.length;
		//求出异或前缀和
		int[] dp = new int[len + 1];
		for(int i = 1;i<dp.length;i++) dp[i] = dp[i-1] ^ arr[i-1];
		//通过异或前缀和，求出最终结果值
		int index = 0;
		int[] res = new int[queries.length];
		for(int[] query:queries){
			res[index++] = dp[query[0]] ^ dp[query[1] + 1];
		}
		return res;
	}
}
