package com.bingoabin.algorithm.math;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bingoabin
 * @date 2021/6/21 9:55
 */
public class BinaryWatch {
	//Leetcode 401. 二进制手表
	//案例：小时：8 4 2 1  分钟：32 16 8 4 2 1  输入：turnedOn = 1  输出：["0:01","0:02","0:04","0:08","0:16","0:32","1:00","2:00","4:00","8:00"]
	//分析：给出亮灯数，求出可能的时间，比如亮了一盏灯，那么可能的时间有多少种情况，计算出来
	//思路：双重循环，小时能取的范围0-11 分钟能取的范围0-59，统计出这两个值的bit数，如果之和等于target，那么就加入到结果集中
	public static void main(String[] args) {
		//[0:01, 0:02, 0:04, 0:08, 0:16, 0:32, 1:00, 2:00, 4:00, 8:00]
		System.out.println(readBinaryWatch(1));

		System.out.println(getBit(59));
		System.out.println(Integer.bitCount(59));
	}

	public static List<String> readBinaryWatch(int turnedOn) {
		List<String> res = new ArrayList<String>();
		for (int h = 0; h < 12; h++) {
			for (int m = 0; m < 60; m++) {
				// if (Integer.bitCount(h) + Integer.bitCount(m) == turnedOn) {
				// 	res.add(String.format("%d:%02d", h, m));
				// }
				if (getBit(h) + getBit(m) == turnedOn) {
					res.add(String.format("%d:%02d", h, m));
				}
			}
		}
		return res;
	}

	public static int getBit(int num) {
		int count = 0;
		while (num > 0) {
			count++;
			num = num & (num - 1);
		}
		return count;
	}
}
