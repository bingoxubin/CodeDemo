//替换空格
//请实现一个函数，将一个字符串中的每个空格替换成“%20”。例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。

package com.bingoabin.newcoder;

public class _02ReplaceString {
	public class Solution {
		public String replaceSpace(StringBuffer str) {
			char[] temp = str.toString().toCharArray();
			StringBuilder result = new StringBuilder();
			for (int i = 0; i < temp.length; i++) {
				if (temp[i] == ' ') {
					result.append("%20");
				} else {
					result.append(temp[i]);
				}
			}
			return result.toString();
		}
	}
}
