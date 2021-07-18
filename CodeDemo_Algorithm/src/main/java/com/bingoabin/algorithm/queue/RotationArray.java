package com.bingoabin.algorithm.queue;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author xubin03
 * @date 2021/5/26 10:21 上午
 */
public class RotationArray {
	//NC110 旋转数组  https://www.nowcoder.com/practice/e19927a8fd5d477794dac67096862042?tpId=117&&tqId=37834&rp=1&ru=/ta/job-code-high&qru=/ta/job-code-high/question-ranking
	//样例：输入：6,2,[1,2,3,4,5,6]  输出 [5,6,1,2,3,4]
	//分析：将数组按照右移的方式，向右移2的位置，后面的往前补上，即可
	//思路：方式一：通过数组模拟移动，先记下最后一个，然后从倒数第二个往后移，移动m次
	//     方式二：将数组放到双端队列中，后面的放到前面，放m次
	//     方式三：用额外数组，将原先的数组放入到新数组中，(i+m)%n
	//     方式四：将数组翻转，然后前面m个翻转，后面n-m个翻转，就是结果
	public static void main(String[] args) {
		int[] arr = {1, 2, 3, 4, 5, 6};
		// System.out.println(Arrays.toString(solve1(6, 2, arr)));
		// System.out.println(Arrays.toString(solve2(6, 2, arr)));
		// System.out.println(Arrays.toString(solve3(6, 2, arr)));
		System.out.println(Arrays.toString(solve4(6, 2, arr)));
	}

	//方式一：通过数组模拟移动，先记下最后一个，然后从倒数第二个往后移，移动m次
	public static int[] solve1(int n, int m, int[] a) {
		for (int i = 0; i < m; i++) {
			int temp = a[n - 1];
			for (int j = n - 2; j >= 0; j--) {
				a[j + 1] = a[j];
			}
			a[0] = temp;
		}
		return a;
	}

	//方式二：将数组放到双端队列中，后面的放到前面，放m次
	public static int[] solve2(int n, int m, int[] a) {
		Deque<Integer> queue = new LinkedList<Integer>();
		for (int i : a) {
			queue.offerLast(i);
		}
		for (int i = 0; i < m; i++) {
			queue.offerFirst(queue.pollLast());
		}
		for (int i = 0; i < n; i++) {
			a[i] = queue.pollFirst();
		}
		return a;
	}

	//方式三：用额外数组，将原先的数组放入到新数组中，(i+m)%n
	public static int[] solve3(int n, int m, int[] a) {
		int[] res = new int[n];
		for (int i = 0; i < n; i++) {
			res[(i + m) % n] = a[i];
		}
		return res;
	}

	//方式四：将数组翻转，然后前面m个翻转，后面n-m个翻转，就是结果
	//  1 2 3 4 5 6
	//  6 5 4 3 2 1   全部翻转
	//  5 6 1 2 3 4   前面m个 和后面n-m个分别翻转
	public static int[] solve4(int n, int m, int[] a) {
		m = m % n;
		reverse(0, n - 1, a);
		reverse(0, m - 1, a);
		reverse(m, n - 1, a);
		return a;
	}

	//将下标从m - n的数据，在arr中进行交换
	public static void reverse(int m, int n, int[] arr) {
		while (m < n) {
			int temp = arr[m];
			arr[m++] = arr[n];
			arr[n--] = temp;
		}
	}
}
