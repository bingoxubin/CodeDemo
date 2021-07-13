//构建乘积数组
//给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。不能使用除法。
package com.bingoabin.newcoder;

public class _51BuildProductArray {
	public class Solution {
		public int[] multiply(int[] A) {
			if (A == null || A.length == 0) {
				return new int[1];
			}
			int[] B = new int[A.length];
			int tmp1 = 1;
			int[] tmp2 = new int[A.length];
			tmp2[A.length - 1] = 1;
			for (int i = 1; i < A.length; i++) {
				tmp2[A.length - i - 1] = tmp2[A.length - i] * A[A.length - i];
			}
			for (int i = 0; i < A.length; i++) {
				if (i != 0) {
					tmp1 *= A[i - 1];
				}
				B[i] = tmp1 * tmp2[i];
			}
			return B;
		}
	}

	public class Solution1 {
		public int[] multiply(int[] A) {
			int[] B = new int[A.length];
			B[0] = 1;
			//计算下三角部分（即算出B[i]的左半部分),计算过的存在B[i-1]中。
			for (int i = 1; i < B.length; i++) {
				B[i] = B[i - 1] * A[i - 1];
			}
			//计算上三角（即算出B[i]的右半部分），计算过的存在temp中。
			int temp = 1;
			for (int j = B.length - 2; j >= 0; j--) {
				temp *= A[j + 1];
				B[j] *= temp;
			}
			return B;
		}
	}

	public class Solution2 {
		public int[] multiply(int[] A) {
			int[] B = new int[A.length];
			int temp;
			for (int i = 0; i < B.length; i++) {
				temp = 1;
				for (int j = 0; j < A.length; j++) {
					if (i != j) {
						temp *= A[j];
					}
				}
				B[i] = temp;
			}
			return B;
		}
	}
}
