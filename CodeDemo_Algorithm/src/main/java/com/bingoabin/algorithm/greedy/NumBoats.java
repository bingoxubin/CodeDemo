package com.bingoabin.algorithm.greedy;

import java.util.Arrays;

/**
 * @Author: xubin34
 * @Date: 2021/8/26 2:14 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class NumBoats {
	//Leetcode 881. 救生艇
	//示例：输入：people = [1,2], limit = 3 输出：1
	//     输入：people = [3,2,2,1], limit = 3  输出：3
	//     输入：people = [3,5,3,4], limit = 5  输出：4
	//分析：一个人体重的数组，一条船最多可以带2个人，并且只能带limit重量的人，求至少要多少船
	//思路：用贪心算法，先对数组进行排序，当最轻的跟最重的看能不能一条船，right-- ;如果不行，那么最重的单独一条船，如果可以的话left++ right--
	public static void main(String[] args) {
		int[] person = {3, 4, 5, 4};
		System.out.println(numRescueBoats(person, 3));
	}

	public static int numRescueBoats(int[] people, int limit) {
		int res = 0;
		Arrays.sort(people);
		int left = 0;
		int right = people.length - 1;
		while (left <= right) {
			if (people[left] + people[right] <= limit) {
				left++;
			}
			right--;
			res++;
		}
		return res;
	}
}
