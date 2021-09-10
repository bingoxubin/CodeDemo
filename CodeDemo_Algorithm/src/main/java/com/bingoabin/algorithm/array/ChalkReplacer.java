package com.bingoabin.algorithm.array;

/**
 * @Author: xubin34
 * @Date: 2021/9/10 9:33 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class ChalkReplacer {
	//Leetcode 1894. 找到需要补充粉笔的学生编号
	//示例：输入：chalk = [5,1,5], k = 22 输出：0
	//     输入：chalk = [3,4,1,2], k = 25 输出：1
	//分析：给一个学生数组，一共学生数为数组长度，每个学生回答问题需要消耗数组中值的粉笔，一共k个粉笔，学生轮流作答，达到多少个人时需要补充粉笔
	//思路：先求出所有学生需要消耗的总的粉笔，然后跟k取余，然后一次遍历即可，如果当前位置需要粉笔大于k，那么就返回
	public static void main(String[] args) {
		ChalkReplacer chalkReplacer = new ChalkReplacer();
		int[] chalk = {3, 4, 1, 2};
		System.out.println(chalkReplacer.chalkReplacer(chalk, 25));
	}

	//思路：先求出所有学生需要消耗的总的粉笔，然后跟k取余，然后一次遍历即可，如果当前位置需要粉笔大于k，那么就返回
	public int chalkReplacer(int[] chalk, int k) {
		long sum = 0l;
		for (int i = 0; i < chalk.length; i++) {
			sum += chalk[i];
		}
		k %= sum;
		for (int i = 0; i < chalk.length; i++) {
			if (chalk[i] > k) {
				return i;
			}
			k -= chalk[i];
		}
		return -1;
	}
}
