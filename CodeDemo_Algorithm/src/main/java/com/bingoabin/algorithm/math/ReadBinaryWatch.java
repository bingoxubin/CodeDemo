package com.bingoabin.algorithm.math;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bingoabin
 * @date 2022/7/4 14:29
 */
public class ReadBinaryWatch {
	//Leetcode 401. 二进制手表
	//示例：示例：
	//      输入: n = 1
	//      返回: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
	//分析：二进制手表顶部有 4 个 LED 代表 小时（0-11），底部的 6 个 LED 代表 分钟（0-59）。
	//      每个 LED 代表一个 0 或 1，最低位在右侧。
	//      例如，上面的二进制手表读取 “3:25”。
	//      给定一个非负整数 n 代表当前 LED 亮着的数量，返回所有可能的时间。
	//思路：
	public static void main(String[] args) {
		ReadBinaryWatch readBinaryWatch = new ReadBinaryWatch();
		System.out.println(readBinaryWatch.readBinaryWatch(1));
	}

	public List<String> readBinaryWatch(int num) {
		List<String> res = new ArrayList<>();
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 60; j++) {
				if (Integer.bitCount(i) + Integer.bitCount(j) == num) {
					res.add(String.format("%d:%02d", i, j));
				}
			}
		}
		return res;
	}
}
