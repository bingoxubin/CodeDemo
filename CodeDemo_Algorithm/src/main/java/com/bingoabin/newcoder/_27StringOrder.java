//字符串的排列
//输入一个字符串,按字典序打印出该字符串中字符的所有排列。例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
//输入一个字符串,长度不超过9(可能有字符重复),字符只包括大小写字母。

//思路：
//考点：全排列和字典序
//     查找字典序的下一个：
//     1. 从序列的最后一个开始找，找到第一个下降的数from。
//     2. 从这个数的下一个开始查找最后一个比from这个数大的数to。（最小的大数）
//     3. 交换from和to
//     4. 将from之后到to的数反转

package com.bingoabin.newcoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

public class _27StringOrder {
	public class Solution {
		public ArrayList<String> Permutation(String str) {
			ArrayList<String> result = new ArrayList<String>();
			if (str == null || str.length() == 0) {
				return result;
			}

			char[] chars = str.toCharArray();
			TreeSet<String> temp = new TreeSet<>();
			Permutation(chars, 0, temp);
			result.addAll(temp);
			return result;
		}

		public void Permutation(char[] chars, int begin, TreeSet<String> result) {
			if (chars == null || chars.length == 0 || begin < 0 || begin > chars.length - 1) {
				return;
			}

			if (begin == chars.length - 1) {
				result.add(String.valueOf(chars));
			} else {
				for (int i = begin; i <= chars.length - 1; i++) {
					swap(chars, begin, i);

					Permutation(chars, begin + 1, result);

					swap(chars, begin, i);
				}
			}
		}

		public void swap(char[] x, int a, int b) {
			char t = x[a];
			x[a] = x[b];
			x[b] = t;
		}
	}

	public class Solution1 {
		public ArrayList<String> Permutation(String str) {
			ArrayList<String> list = new ArrayList<String>();
			char[] words = str.toCharArray();
			Arrays.sort(words);
			char[] ends = str.toCharArray();
			reverse(ends, 0, ends.length - 1);
			String endStr = new String(ends);

			if (str == null || "".equals(str.trim())) {
				return list;
			}
			list.add(str);
			String s = null;
			while (!new String(words).equals(endStr)) {
				s = findNextSequence(words);
				list.add(s);
			}
			return list;
		}

		public String findNextSequence(char[] words) {
			int fromIndex = 0, toIndex = 0;
			for (int i = words.length - 1; i > 0; i--) {
				if (words[i - 1] < words[i]) {    //第一次下降
					fromIndex = i - 1;
					for (int j = i; j < words.length; j++) {
						if (words[j] > words[fromIndex]) {
							toIndex = j;
						} else {
							break;
						}
					}
					swap(words, fromIndex, toIndex);
					reverse(words, fromIndex + 1, words.length - 1);
					break;
				}
			}
			return new String(words);
		}

		public void swap(char[] words, int a, int b) {
			char temp = words[a];
			words[a] = words[b];
			words[b] = temp;
		}

		public void reverse(char[] words, int start, int end) {
			while (start < end) {
				swap(words, start++, end--);
			}
		}

	}
}
