package com.bingoabin.algorithm.string;

/**
 * @Author: xubin34
 * @Date: 2021/10/15 10:09 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class CountAndSay {
	//Leetcode 38. 外观数列
	// 示例：输入：n = 4
	//      输出："1211"
	//      解释：
	//      countAndSay(1) = "1"
	//      countAndSay(2) = 读 "1" = 一 个 1 = "11"
	//      countAndSay(3) = 读 "11" = 二 个 1 = "21"
	//      countAndSay(4) = 读 "21" = 一 个 2 + 一 个 1 = "12" + "11" = "1211"
	//分析：从1开始，第二项为11，第三项为21 第4项为1211  第5项为111221
	//思路：按照正常的数数逻辑
	public static void main(String[] args) {
		CountAndSay countAndSay = new CountAndSay();
		System.out.println(countAndSay.countAndSay1(5));
		System.out.println(countAndSay.countAndSay2(5));
		System.out.println(countAndSay.countAndSay2(5));
	}

	//迭代方式
	public String countAndSay1(int n) {
		//初始string
		String str = "1";
		//从第二个开始往后推算
		for (int i = 2; i <= n; ++i) {
			StringBuilder sb = new StringBuilder();
			int start = 0;
			int pos = 0;

			while (pos < str.length()) {
				while (pos < str.length() && str.charAt(pos) == str.charAt(start)) {
					pos++;
				}
				sb.append(Integer.toString(pos - start)).append(str.charAt(start));
				start = pos;
			}
			str = sb.toString();
		}

		return str;
	}

	//递归方式
	public String countAndSay2(int n) {
		String result = "";
		if (n == 1) {
			return "1";
		}
		result = countAndSay2(n - 1) + "*";
		System.out.println(result);
		StringBuilder sb = new StringBuilder();
		char[] temp = result.toCharArray();
		int count = 1;
		for (int i = 0; i < temp.length - 1; i++) {
			if (temp[i] == temp[i + 1]) {
				count++;
			} else {
				sb.append("" + count + temp[i]);
				count = 1;
			}
		}
		return sb.toString();
	}

	//自己实现一遍
	public String countAndSay3(int n) {
		String res = "1";
		for (int i = 2; i <= n; i++) {
			StringBuilder buffer = new StringBuilder();
			int start = 0; //当前字符串的开始下标位置
			int end = 0; //统计跟当前字符一直一样的，最后一个位置

			//当end的位置小于 字符串的长度 一直遍历
			while (end < res.length()) {
				//如果 不断相等 那么一直往后累加end
				while (end < res.length() && res.charAt(start) == res.charAt(end)) {
					end++;
				}
				//更新buffer的值
				buffer.append(end - start).append(res.charAt(start));
				//将开始的位置 挪到最后的位置
				start = end;
			}
			//字符串的值 往后更新 统计下一个值
			res = buffer.toString();
		}
		return res;
	}
}
