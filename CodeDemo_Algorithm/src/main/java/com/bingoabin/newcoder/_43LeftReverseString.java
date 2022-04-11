//左旋转字符串
//汇编语言中有一种移位指令叫做循环左移（ROL），现在有个简单的任务，就是用字符串模拟这个指令的运算结果。
// 对于一个给定的字符序列S，请你把其循环左移K位后的序列输出。例如，字符序列S=”abcXYZdef”,要求输出循环左移3位后的结果，即“XYZdefabc”。是不是很简单？OK，搞定它！

//若str的长度小于等于1，直接返回；
// 否则，将n对str的长度取模得到count，将下标为count开始的字符串拼接上前面的count个字符后返回。
package com.bingoabin.newcoder;

public class _43LeftReverseString {
	public class Solution {
		public String LeftRotateString(String str, int n) {
			if (str.length() == 0) {
				return str;
			}
			StringBuffer buffer = new StringBuffer(str);
			StringBuffer buffer1 = new StringBuffer(str);
			StringBuffer buffer2 = new StringBuffer();
			buffer.delete(0, n);
			buffer1.delete(n, str.length());
			buffer2.append(buffer.toString()).append(buffer1.toString());
			return buffer2.toString();
		}
	}

	public class Solution1 {
		public String LeftRotateString(String str, int n) {
			if (str.length() <= 1) {    //注意，str=null,返回null;str="",返回""
				return str;
			}
			int count = n % str.length();
			return str.substring(count) + str.substring(0, count);
		}
	}
}
