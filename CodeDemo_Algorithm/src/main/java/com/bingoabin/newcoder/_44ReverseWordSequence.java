//翻转单词顺序列
//牛客最近来了一个新员工Fish，每天早晨总是会拿着一本英文杂志，写些句子在本子上。同事Cat对Fish写的内容颇感兴趣，有一天他向Fish借来翻看，但却读不懂它的意思。
// 例如，“student. a am I”。后来才意识到，这家伙原来把句子单词的顺序翻转了，正确的句子应该是“I am a student.”。Cat对一一的翻转这些单词顺序可不在行，你能帮助他么？

//利用String的split方法
//利用栈

package com.bingoabin.newcoder;

import java.util.Stack;

public class _44ReverseWordSequence {
	public class Solution {
		public String ReverseSentence(String str) {
			if (str == null) {
				return null;
			}
			if ("".equals(str.trim())) {
				return str;
			}
			String string = str;
			String[] strings = string.split(" ");
			StringBuilder sBuilder = new StringBuilder();
			for (int i = strings.length - 1; i >= 0; i--) {
				if (i == 0) {
					sBuilder.append(strings[i]);
				} else {
					sBuilder.append(strings[i]);
					sBuilder.append(" ");
				}
			}

			String string2 = sBuilder.toString();
			return string2;
		}
	}

	public class Solution1 {
		public String ReverseSentence(String str) {
			if (str == null || "".equals(str.trim())) {
				return str;
			}
			String[] words = str.split(" ");
			if (words.length == 1) {
				return words[0];
			}
			StringBuffer sb = new StringBuffer();
			for (int i = words.length - 1; i >= 0; i--) {
				sb.append(words[i] + " ");
			}
			return sb.toString().substring(0, sb.length() - 1);
		}
	}

	public class Solution2 {
		public String ReverseSentence(String str) {
			Stack<Character> stack = new Stack<Character>();
			StringBuffer sb = new StringBuffer();
			if (str.length() <= 1) {
				return str;
			}
			for (int i = str.length() - 1; i >= 0; i--) {
				if (str.charAt(i) != ' ') {
					stack.push(str.charAt(i));
				} else {
					while (!stack.empty()) {
						char c = stack.pop();
						sb.append(c);
					}
					sb.append(" ");
				}
			}
			while (!stack.empty()) {
				char c = stack.pop();
				sb.append(c);
			}
			return sb.toString();
		}
	}
}
