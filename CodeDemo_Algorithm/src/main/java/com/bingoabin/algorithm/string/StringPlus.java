package com.bingoabin.algorithm.string;

/**
 * @author xubin03
 * @date 2021/6/6 12:26 下午
 */
public class StringPlus {
	//NC1 大数加法
	//样例：输入"1","99" 输出：100
	//分析：以字符串的形式读入两个数字，编写一个函数计算它们的和，以字符串形式返回。
	//思路：定义一个stringbuilder，然后从最末尾往前加，考虑到进位，用flag表示，如果flag>0，最终还是要合并上去，最终将stringbuilder翻转
	public static void main(String[] args) {
		String str1 = "1";
		String str2 = "99";
		System.out.println(solve(str1, str2));

		String str3 = "99999999999999999999999999999999999999999999999999999999999994";
		String str4 = "9";
		System.out.println(solve1(str3, str4));
	}

	//定义一个stringbuilder，然后从最末尾往前加，考虑到进位，用flag表示，如果flag>0，最终还是要合并上去，最终将stringbuilder翻转
	public static String solve(String s, String t) {
		StringBuilder res = new StringBuilder();
		int lens = s.length() - 1;
		int lent = t.length() - 1;
		int flag = 0;
		while (lent >= 0 || lens >= 0 || flag > 0) {
			int i = 0, j = 0;
			if (lens >= 0) {
				i = s.charAt(lens--) - '0';
			}
			if (lent >= 0) {
				j = t.charAt(lent--) - '0';
			}
			int sum = i + j + flag;
			flag = sum / 10;
			res.append(sum % 10);
		}
		return res.reverse().toString();
	}

	//少定义两个变量，用三目表达式解决，但是要考虑括号，否则顺序就错乱了
	public static String solve1(String s, String t) {
		StringBuilder res = new StringBuilder();
		int lens = s.length() - 1;
		int lent = t.length() - 1;
		int flag = 0;
		while (lens >= 0 || lent >= 0 || flag > 0) {
			int sum = (lens >= 0 ? s.charAt(lens--) - '0' : 0) + (lent >= 0 ? t.charAt(lent--) - '0' : 0) + flag;
			flag = sum / 10;
			res.append(sum % 10);
		}
		return res.reverse().toString();
	}
}
