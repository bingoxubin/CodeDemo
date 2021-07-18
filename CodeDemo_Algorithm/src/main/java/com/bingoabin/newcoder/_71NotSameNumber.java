package com.bingoabin.newcoder;

/**
 * @author xumaosheng
 * 一个数组，除一个元素外其它都是两两相等，求那个元素?
 * @date 2019/12/19 16:46
 */
public class _71NotSameNumber {
	public static int find1From2(int[] a) {
		int len = a.length, res = 0;
		for (int i = 0; i < len; i++) {
			res = res ^ a[i];
		}
		return res;
	}

	public static void main(String[] args) {
		int[] arr = {1, 2, 1, 2, 3, 4, 4, 3, 5};
		System.out.println(find1From2(arr));
	}
}
