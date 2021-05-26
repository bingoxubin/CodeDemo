//栈和队列
//滑动窗口的最大值
//给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，
// 那么一共存在6个滑动窗口，他们的最大值分别为{4,4,6,6,6,5}； 针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个：
// {[2,3,4],2,6,2,5,1}， {2,[3,4,2],6,2,5,1}， {2,3,[4,2,6],2,5,1}， {2,3,4,[2,6,2],5,1}， {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。
package com.bingoabin.newcoder;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class _64StackAndQueue {
	public class Solution {
		public ArrayList<Integer> maxInWindows(int[] num, int size) {
			ArrayList<Integer> result = new ArrayList<>();
			if (num.length < 1 || size < 1 || num.length < size || num == null) {
				return result;
			}
			//0 1 2 3 4
			for (int i = 0; i <= num.length - size; i++) {
				int max = 0;
				for (int j = i; j < i + size; j++) {
					if (num[j] > max) {
						max = num[j];
					}
				}
				result.add(max);
			}
			return result;
		}
	}

	public class Solution1 {
		public ArrayList<Integer> maxInWindows(int[] num, int size) {
			ArrayList<Integer> result = new ArrayList();
			if (num == null || size <= 0 || num.length < size) return result;
			ArrayDeque<Integer> deque = new ArrayDeque();
			for (int i = 0; i < num.length; i++) {
				//2 3 4 2 6 2 5 1
				while (!deque.isEmpty() && num[i] >= num[deque.peekLast()]) {
					deque.pollLast();
				}
				deque.addLast(i);
				if (i - deque.peekFirst() + 1 > size) {
					deque.pollFirst();
				}
				if (i + 1 >= size) {
					result.add(num[deque.peekFirst()]);
				}
			}
			return result;
		}
	}
}
