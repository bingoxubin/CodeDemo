package com.bingoabin.algorithm.string;

public class CountXingNums {
	//Leetcode 6104. 统计星号
	//示例：输入：s = "l|*e*et|c**o|*de|"
	//      输出：2
	//      解释：不在竖线对之间的字符加粗加斜体后，得到字符串："l|*e*et|c**o|*de|" 。
	//      第一和第二条竖线 '|' 之间的字符不计入答案。
	//      同时，第三条和第四条竖线 '|' 之间的字符也不计入答案。
	//      不在竖线对之间总共有 2 个星号，所以我们返回 2 。
	//分析：给你一个字符串 s ，每 两个 连续竖线 '|' 为 一对 。换言之，第一个和第二个 '|' 为一对，第三个和第四个 '|' 为一对，以此类推。
	//      请你返回 不在 竖线对之间，s 中 '*' 的数目。
	//      注意，每个竖线 '|' 都会 恰好 属于一个对。
	//思路：将字符串进行字符遍历，统计|的个数，如果个数是偶数，且遇到*号然后进行累加
	public static void main(String[] args) {
		String s = "l|*e*et|c**o|*de|";
		CountXingNums countXingNums = new CountXingNums();
		System.out.println(countXingNums.countAsterisks(s));
	}

	public int countAsterisks(String s) {
		char[] arrs = s.toCharArray();
		int res = 0;
		int cnt = 0; //竖线的个数
		for (int arr : arrs) {
			if (arr == '|') {
				cnt++;
			}
			if ((cnt & 1) == 0 && arr == '*') {
				res++;
			}
		}
		return res;
	}
}
