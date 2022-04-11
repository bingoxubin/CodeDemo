package com.bingoabin.algorithm.array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author bingoabin
 * @date 2021/7/3 1:00
 */
public class MaxIceCream {
	//LeetCode 1833. 雪糕的最大数量
	//示例：costs = [1,3,2,4,1], coins = 7 返回 4
	//分析：costs为雪糕的定价，一共n根，conis是你一共有多少钱，可以买到最多数量的雪糕
	//思考：方式一：采用priorityqueue，将数组中的花费放入到优先级队列中，然后逐个弹出，如果coins - 弹出的值 >= 0 那么就count++
	//     方式二：采用先排序  然后coins 减去从左边开始每一个值，如果减去后>=0 那么count++

	public static void main(String[] args) {
		int[] costs = {1, 3, 2, 4, 1};
		System.out.println(maxIceCream1(costs, 7));
		System.out.println(maxIceCream2(costs, 7));
	}

	//方式一：采用priorityqueue，将数组中的花费放入到优先级队列中，然后逐个弹出，如果coins - 弹出的值 >= 0 那么就count++
	public static int maxIceCream1(int[] costs, int coins) {
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1.compareTo(o2);
			}
		});
		for (int cost : costs) {
			queue.offer(cost);
		}
		int count = 0;
		while (!queue.isEmpty() && coins - queue.peek() >= 0) {
			count++;
			coins -= queue.poll();
		}
		return count;
	}

	//方式二：采用先排序  然后coins 减去从左边开始每一个值，如果减去后>=0 那么count++
	public static int maxIceCream2(int[] costs, int coins) {
		Arrays.sort(costs);
		int count = 0;
		for (int i = 0; i < costs.length; i++) {
			if (coins - costs[i] >= 0) {
				count++;
				coins -= costs[i];
			} else {
				break;
			}
		}
		return count;
	}
}
