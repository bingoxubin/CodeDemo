package com.bingoabin.matches;
import java.util.ArrayList;
import java.util.List;
public class WildCardMatcher{

	/**
	 * 仅为了方便实验
	 *
	 * @param input
	 * @param pattern
	 * @return
	 */
	public static List<String> matches(String input, String pattern) {
		String[] pa = pattern.split("\\*+");    // 分割不是重点，故未做重点实现
		return matches(input, pa);
	}

	/**
	 * 从input中查找通配符序列
	 *
	 * @param input
	 * @param patterns
	 * @return
	 */
	public static List<String> matches(CharSequence input, String[] patterns) {
		int n = input.length(), m = patterns.length;
		List<String> result = new ArrayList<String>();
		for (int i = 0; i < n; ) {
			int left = -1, right = -1;
			for (int j = 0; j < m; j++) {   // 以i为起点，执行m趟匹配，每趟i至少前进p[j].length长度
				long region = lookBehind(input, i, patterns[j]);
				if (j != 0 && region >= 0) {   // 模式序列的第二个开始使用贪婪匹配
					long greedyRegion;
					for (int k = (int) region + 1; ; k = (int) greedyRegion + 1) {
						greedyRegion = lookBehind(input, k, patterns[j]);
						if (greedyRegion > 0)    // 贪婪找到，继续贪婪尝试
							region = greedyRegion;
						else break;
					}
				}
				if (region < 0) {   // pattern[j]失败，则本趟失败
					i = ((int) -region) + 1;
					break;
				} else {
					i = (int) region + 1;
					if (j == 0)     // 模式序列的第一个找到，记左边界，在高32位
						left = (int) (region >> 32);
					if (j == m - 1)     // 模式序列的最后一个找到，记右边界，在低32位
						right = (int) region;
				}
			}
			if (left >= 0 && right >= 0) result.add(input.subSequence(left, right + 1).toString());
		}
		return result;
	}

	/**
	 * 在input的i位置开始向后扫描非贪婪查找pattern，在pattern尾匹配时回溯确认
	 *
	 * @param in
	 * @param i
	 * @param pattern
	 * @return
	 */
	public static long lookBehind(CharSequence in, int i, CharSequence pattern) {
		int len = in.length(), pLen = pattern.length(), _pMax = pLen - 1;
		char pEnd = pattern.charAt(_pMax);
		if (len - i >= pLen) {
			for (i = i + _pMax; i < len; i++) {  // 以 i + pLen - 1 起步
				if (in.charAt(i) == pEnd || pEnd == '?') {     // 与pa末尾相同，i即右边界
					if (pLen == 1) return ((long) i) << 32 | i;
					for (int j = i - 1; j >= i - _pMax; j--) {   // 则至多回溯pLen长找左边界
						char p = pattern.charAt(_pMax - i + j);
						if (in.charAt(j) == p || p == '?') {
							if (j == i - _pMax)     // 找到左边界即j
								return ((long) j) << 32 | i;
						} else break;
					}
				}
			}
		}
		return -i;
	}
}

