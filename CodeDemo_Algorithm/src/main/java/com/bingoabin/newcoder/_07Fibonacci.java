//斐波那契数列
//大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）。n<=39

//思路
// 		递归
// 		若n==0,返回0；
// 		若n<=2;返回n;
// 		否则，返回Fibonacci(n-1)+Fibonacci(n-2);
// 		递推
// 		拿两个变量记录上两个值和一个临时变量记录当前相加结果即可

package com.bingoabin.newcoder;

public class _07Fibonacci {
	public class Solution {
		public int Fibonacci(int n) {
			//0 1 1 2 3
			if (n == 0) {
				return 0;
			}
			if (n == 1) {
				return 1;
			}
			if (n == 2) {
				return 1;
			}
			if (n >= 3) {
				return Fibonacci(n - 2) + Fibonacci(n - 1);
			}
			return 0;
		}
	}

	public class Solution1 {
		public int Fibonacci(int n) {
			if (n == 0) {
				return 0;
			} else if (n <= 2) {
				return 1;
			} else {
				int i = Fibonacci(n - 1) + Fibonacci(n - 2);
				return i;
			}
		}
	}

	public class Solution2 {
		public int Fibonacci(int n) {
			int pre = 1, last = 1, temp;
			if (n <= 0) {
				return 0;
			} else if (n <= 2) {
				return 1;
			}
			while (n > 2) {
				n--;//次数减1
				temp = pre + last;
				pre = last;
				last = temp;
			}
			return last;
		}
	}

	public class Solution3 {
		public int Fibonacci(int n) {
			int pre = 1, last = 1;
			if (n <= 0) {
				return 0;
			} else if (n <= 2) {
				return 1;
			}
			while (n > 2) {
				n--;//次数减1
				last = pre + last;
				pre = last - pre;
			}
			return last;
		}
	}

	public class Solution4 {
		public int Fibonacci(int n) {
			int pre = 1;
			int last = 1;
			int result = 0;
			if (n <= 0) {
				return 0;
			}
			if (n <= 2) {
				return 1;
			}
			while (n > 2) {
				n--;
				result = pre + last;
				pre = last;
				last = result;
			}
			return result;
		}
	}
}
