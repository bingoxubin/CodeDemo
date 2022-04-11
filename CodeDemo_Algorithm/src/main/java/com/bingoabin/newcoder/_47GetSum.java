//求1-----n的和
//求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。

//利用&&的短路特性代替if
package com.bingoabin.newcoder;

public class _47GetSum {
	public class Solution {
		public int Sum_Solution(int n) {
			int sum = (int) (Math.pow(n, 2) + n);
			return sum >> 1;
		}
	}

	public class Solution1 {
		public int Sum_Solution(int n) {
			int result = n;
			boolean flag = result > 0 && (result += Sum_Solution(n - 1)) > 0;
			return result;
		}
	}
}
