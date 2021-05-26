package com.bingoabin.newcoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author bingoabin
 * @date 2020/12/8 23:19
 */
public class _00PeerTest {
	public static void main(String[] args) {
		System.out.println(countWine(2, 12));

		System.out.println(Maximumlength("cbacb"));
		System.out.println(Maximumlength("abaaaxxzzbbreqbtqrwqryiutuocccc"));
		System.out.println(Maximumlength2("cbacb"));
		System.out.println(Maximumlength2("abaaaxxzzbbreqbtqrwqryiutuocccc"));
		System.out.println("================" + Maximumlength3("abaaaxxzzbbreqbtqrwqryiutuocccc"));

		System.out.println(getN(8, 3, 3));
		System.out.println(getN(3, 4, 2));
		System.out.println(getN(6, 3, 2));
		System.out.println(getN(6, 3, 5));
	}

	//一共n元，一瓶啤酒m元，一共喝多少瓶，2酒瓶换一瓶，4酒瓶盖换一瓶
	//用例：输入2,12  输出19
	public static int countWine(int m, int n) {
		int result = n / m;
		int jp = result;
		int pg = result;
		while (jp >= 2 || pg >= 4) {
			int temp = jp / 2 + pg / 4;
			jp %= 2;
			pg %= 4;
			result += temp;
			jp += temp;
			pg += temp;
		}
		return result;
	}

	//字符串的子序列3n 满足n个a，n个b，n个c
	//输入cbacb 输出0
	//输入abaabbcccc 输出6
	//方式一：二分
	public static int Maximumlength(String x) {
		int n = x.length();
		int left = 0, right = n, ans = 0;
		while (left <= right) {
			int mid = (left + right) / 2;
			if (check(mid, x)) {
				ans = mid;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return ans * 3;
	}

	public static boolean check(int mid, String x) {
		int n = x.length();
		int a = 0, b = 0, c = 0;
		for (int i = 0; i < n; i++) {
			if (a < mid) {
				if (x.charAt(i) == 'a') {
					a++;
				}
			} else if (b < mid) {
				if (x.charAt(i) == 'b') {
					b++;
				}
			} else if (x.charAt(i) == 'c') {
				c++;
			}
		}
		return c >= mid;
	}

	//输入cbacb 输出0
	//输入abaabbcccc 输出6
	public static int Maximumlength2(String x) {
		int n = x.length();
		List<Integer> a = new ArrayList<Integer>();
		List<Integer> c = new ArrayList<Integer>();
		int[] presum = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			presum[i] = presum[i - 1];
			if (x.charAt(i - 1) == 'a') {
				a.add(i - 1);
			}
			if (x.charAt(i - 1) == 'b') {
				presum[i]++;
			}
			if (x.charAt(i - 1) == 'c') {
				c.add(i - 1);
			}
		}
		System.out.println(a);
		System.out.println(c);
		System.out.println(Arrays.toString(presum));
		Collections.reverse(c);
		int ans = 0;
		for (int i = 0; i < (int) c.size() && i < (int) a.size(); i++) {
			if (c.get(i) < a.get(i)) {
				break;
			}
			if (presum[c.get(i) + 1] - presum[a.get(i)] < i + 1) {
				break;
			}
			ans++;
		}
		return ans * 3;
	}

	//输入cbacb 输出0
	//输入abaabbcccc 输出6
	public static int Maximumlength3(String x) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < x.length(); i++) {
			char temp = x.charAt(i);
			if (temp == 'a' || temp == 'b' || temp == 'c') {
				result.append(temp);
			}
		}
		String ans = result.toString();
		int count = 0;
		StringBuffer a = new StringBuffer();
		StringBuffer b = new StringBuffer();
		StringBuffer c = new StringBuffer();
		for(int i = 0;i<ans.length()/3;i++){
			if(ans.indexOf(a.append('a').toString() + b.append('b').toString() + c.append('c').toString()) != -1) {
				count = a.length();
			}
		}
		return count*3;
	}

	//n个贝壳，牛牛取1，p  牛妹取1，q个  牛牛先取  牛牛赢返回1，牛妹赢返回-1
	//输入8,3,3  返回 -1
	//输入3,4,2  返回 1
	//输入6,3,2  返回 1
	//输入6,3,5  返回 -1
	public static int getN(int n, int p, int q) {
		if (p == q) {
			if ((n % (p + 1)) == 0) {
				return -1;
			} else {
				return 1;
			}
		} else if (p > q) {
			return 1;
		} else {
			if (n < p) {
				return 1;
			} else {
				return -1;
			}
		}
	}

}
