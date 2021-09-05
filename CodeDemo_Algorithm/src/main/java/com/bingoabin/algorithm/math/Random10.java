package com.bingoabin.algorithm.math;

import java.security.SecureRandom;

/**
 * @Author: xubin34
 * @Date: 2021/9/5 11:15 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class Random10 {
	//Leetcode 470. 用 Rand7() 实现 Rand10()
	//示例：输入: 3   输出: [8,1,10]
	//     提示:
	//     rand7 已定义。
	//     传入参数: n 表示 rand10 的调用次数。  已有方法 rand7 可生成 1 到 7 范围内的均匀随机整数，试写一个方法 rand10 生成 1 到 10 范围内的均匀随机整数。
	//分析：通过一个randon7  来产生一个random10
	//思路：
	public static void main(String[] args) {
		Random10 random10 = new Random10();
		System.out.println(random10.rand10_1());
		System.out.println(random10.rand10_2());
		System.out.println(random10.rand10_3());
	}

	//方式一：不借助rand7，直接用Math.random()
	public int rand10_1() {
		return (int) (Math.random() * 10) + 1;
	}

	//方式二：不借助rand7,直接用new Random()
	public int rand10_2() {
		SecureRandom random = new SecureRandom();
		return random.nextInt(10) + 1;
	}

	//方式三：通过借用random7,来实现
	public int rand10_3() {
		//random7 产生 1-7
		//random7 - 1 产生 0-6
		//乘以7  产生 {0，7，14，21，28，35，42}
		//再加上7  产生 1 - 49
		int random = (rand7() - 1) * 7 + rand7();
		//然后 舍弃掉 41 - 49 如果在这个范围，重新随机
		while (random > 40) {
			random = (rand7() - 1) * 7 + rand7();
		}
		return random % 10 + 1;
	}

	//提供的random7
	public int rand7() {
		SecureRandom random = new SecureRandom();
		return random.nextInt(7) + 1;
	}
}
