//包含main函数的栈
//定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。

//思路：
//借助辅助栈实现：
// 	压栈时：若辅助栈为空，则将节点压入辅助栈。否则，当当前节点小于等于辅助栈栈顶元素时将节点压入辅助栈。
// 	出栈时：若辅助栈和数据栈栈顶元素相同，则同时出栈。否则，只出栈数据栈元素。

package com.bingoabin.newcoder;

import java.util.Stack;

public class _20ContainMainStack {
	public class Solution {
		Stack<Integer> stack1 = new Stack<Integer>();
		Stack<Integer> stack2 = new Stack<Integer>();

		public void push(int node) {
			stack1.push(node);
			if (stack2.size() == 0 || stack2.peek() >= node) {
				stack2.push(node);
			}
			if (stack2.peek() < node) {
				stack2.push(stack2.peek());
			}
		}

		public void pop() {
			if (stack1.size() > 0 && stack2.size() > 0) {
				stack1.pop();
				stack2.pop();
			}
		}

		public int top() {
			if (stack1.size() > 0) {
				return stack1.peek();
			}
			return 0;
		}

		public int min() {
			if (stack1.size() > 0 && stack2.size() > 0) {
				return stack2.peek();
			}
			return 0;
		}
	}

	public class Solution1 {
		private Stack stack = new Stack();
		private Stack minStack = new Stack();

		public void push(int node) {
			//当辅助栈为空或者当前节点值小于等于辅助栈栈顶元素才压栈
			if (minStack.isEmpty() || node <= (Integer) minStack.peek()) {
				minStack.push(node);
			}
			stack.push(node);
		}

		public void pop() {
			if (stack.peek() == minStack.peek()) {
				minStack.pop();
			}
			stack.pop();
		}

		public int top() {
			return (Integer) stack.peek();
		}

		public int min() {
			return (Integer) minStack.peek();
		}
	}
}
