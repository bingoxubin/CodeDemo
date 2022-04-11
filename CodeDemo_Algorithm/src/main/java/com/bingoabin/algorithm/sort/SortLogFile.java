package com.bingoabin.algorithm.sort;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author bingoabin
 * @date 2021/6/12 13:31
 */
public class SortLogFile {
	//Leetcode 937 重排序日志文件
	//样例：输入：["a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"]
	//     输出：["g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"]
	//分析：将字符串数组排序，分为数据日志，字母日志，字符串第一个空格前的是标志符，后面的是日志，首先按照日志内容（除去标志符排序）进行排序，字母在数字之前
	//     所有 字母日志 都排在 数字日志 之前。
	//     字母日志 在内容不同时，忽略标识符后，按内容字母顺序排序；在内容相同时，按标识符排序；
	//     数字日志 应该按原来的顺序排列。
	//思路：自定义排序，通过split划分为2部分，然后判断第二部分开头是不是数字，如果都是字母，先按照第二部分排序，然后按照第一部分排序;如果有数字 字母 或者都是数字，那么就按照
	//     如果1是不是数字，那么就返回-1  如果1是数字  那么就看2  如果2是数字 那么就是返回0 正常排序  如果2不是数字 那么就返回1  2排在前面
	public static void main(String[] args) {
		String[] arr = {"a1 9 2 3 1", "g1 act car", "zo4 4 7", "ab1 off key dog", "a8 act zoo"};
		System.out.println(Arrays.toString(reorderLogFiles(arr)));
	}

	public static String[] reorderLogFiles(String[] logs) {
		Arrays.sort(logs, new Comparator<String>() {
			public int compare(String str1, String str2) {
				String[] string1 = str1.split(" ", 2);
				String[] string2 = str2.split(" ", 2);
				boolean isDigit1 = Character.isDigit(string1[1].charAt(0));
				boolean isDigit2 = Character.isDigit(string2[1].charAt(0));
				if (!isDigit1 && !isDigit2) {
					int flag = string1[1].compareTo(string2[1]);
					if (flag != 0) return flag;
					return string1[0].compareTo(string2[1]);
				}
				return isDigit1 ? (isDigit2 ? 0 : 1) : -1;
			}
		});
		return logs;
	}
}
