package com.bingoabin.algorithm.design;

import java.util.PriorityQueue;

/**
 * @Author: xubin34
 * @Date: 2021/8/27 8:15 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class MiddleNum {
	//Leetcode 295. 数据流的中位数
	//示例：addNum(1)
	//     addNum(2)
	//     findMedian() -> 1.5
	//     addNum(3)
	//     findMedian() -> 2
	//分析：一个类，不断的往其中添加元素，求出这个数组中的中位数
	//思路：左边创建一个大顶堆  右边创建一个小顶堆  那么可以判断两个堆中间那个数就是中位数了
	//     来一个数，先判断左边的堆  如果为空，或者来的数比左边堆得顶还小 那么放入到左边堆  判断左边堆的个数 如果比右边堆得个数多超过1个  那么把左边最上面的放到右边去
	//     如果来的数大于右边的堆顶，那么放入到右边堆  如果右边堆的个数大于左边堆得个数 那么将右边堆得最上面这个数放到左边堆即可
	//     求结果，如果左边堆得个数大于右边堆  那么中位数就是左边的堆顶  如果两边的个数相等 那么就是取出两边堆得堆顶数据/2  由于返回的是double 那么只要除以2.0即可
	public static void main(String[] args) {
		MedianFinder medianFinder = new MedianFinder();
		medianFinder.addNum(1);
		medianFinder.addNum(2);
		System.out.println(medianFinder.findMedian());
		medianFinder.addNum(3);
		System.out.println(medianFinder.findMedian());
	}
}

class MedianFinder {
	PriorityQueue<Integer> leftQueue; //大顶堆
	PriorityQueue<Integer> rightQueue; //小顶堆

	public MedianFinder() {
		leftQueue = new PriorityQueue<>((a, b) -> (b - a));
		rightQueue = new PriorityQueue<>((a, b) -> (a - b));
	}

	public void addNum(int num) {
		if (leftQueue.isEmpty() || num < leftQueue.peek()) {
			leftQueue.offer(num);
			if (leftQueue.size() > rightQueue.size() + 1) {
				rightQueue.offer(leftQueue.poll());
			}
		} else {
			rightQueue.offer(num);
			if (rightQueue.size() > leftQueue.size()) {
				leftQueue.offer(rightQueue.poll());
			}
		}
	}

	public double findMedian() {
		if (leftQueue.size() > rightQueue.size()) {
			return leftQueue.peek();
		}
		return (leftQueue.peek() + rightQueue.peek()) / 2.0;
	}
}
