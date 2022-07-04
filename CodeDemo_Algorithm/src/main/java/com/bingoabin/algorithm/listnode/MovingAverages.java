package com.bingoabin.algorithm.listnode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author bingoabin
 * @date 2022/7/4 11:15
 */
public class MovingAverages {
	//Leetcode 346. 数据流中的移动平均值
	//示例：示例:
	//      MovingAverage m = new MovingAverage(3);
	//      m.next(1) = 1
	//      m.next(10) = (1 + 10) / 2
	//      m.next(3) = (1 + 10 + 3) / 3
	//      m.next(5) = (10 + 3 + 5) / 3
	//分析：给定一个整数数据流和一个窗口大小，根据该滑动窗口的大小，计算其所有整数的移动平均值。
	//思路：
	public static void main(String[] args) {
		MovingAverage movingAverage = new MovingAverage(3);
		System.out.println(movingAverage.next(1));
		System.out.println(movingAverage.next(10));
		System.out.println(movingAverage.next(3));
		System.out.println(movingAverage.next(5));
	}
}

class MovingAverage {
	int size;
	int sum = 0;
	Deque<Integer> queue;

	public MovingAverage(int size) {
		this.size = size;
		queue = new LinkedList<>();
	}

	public double next(int val) {
		sum += val;
		queue.offer(val);
		if (queue.size() > size) {
			sum -= queue.poll();
		}
		return (double) 1.0 * sum / queue.size();
	}
}