package com.bingoabin.algorithm.string;

/**
 * @Author: xubin34
 * @Date: 2021/9/1 10:09 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class CompareVersions {
	//Leetcode 165.比较版本号
	//示例：
	// 示例 1：
	// 输入：version1 = "1.01", version2 = "1.001"
	// 输出：0
	// 解释：忽略前导零，"01" 和 "001" 都表示相同的整数 "1"
	// 示例 2：
	// 输入：version1 = "1.0", version2 = "1.0.0"
	// 输出：0
	// 解释：version1 没有指定下标为 2 的修订号，即视为 "0"
	// 示例 3：
	// 输入：version1 = "0.1", version2 = "1.1"
	// 输出：-1
	// 解释：version1 中下标为 0 的修订号是 "0"，version2 中下标为 0 的修订号是 "1" 。0 < 1，所以 version1 < version2
	// 示例 4：
	// 输入：version1 = "1.0.1", version2 = "1"
	// 输出：1
	// 示例 5：
	// 输入：version1 = "7.5.2.4", version2 = "7.5.3"
	// 输出：-1

	//分析：给两个版本号，进行比较，如果相等返回0，如果前面大返回1，如果后面大返回-1;001跟1是相等的
	//思路：先将字符串按照. 进行切分，然后从左边开始比较，比较的位数分别标记为0，0，将个位上的string转成int进行比较
	public static void main(String[] args) {
		String version1 = "1.01";
		String version2 = "1.001";
		System.out.println(compareVersion(version1, version2));
	}

	//先将字符串按照. 进行切分，然后从左边开始比较，比较的位数分别标记为0，0，将个位上的string转成int进行比较
	public static int compareVersion(String v1, String v2) {
		String[] ss1 = v1.split("\\."), ss2 = v2.split("\\.");
		int n = ss1.length, m = ss2.length;
		int i = 0, j = 0;
		while (i < n || j < m) {
			int a = 0, b = 0;
			if (i < n) a = Integer.parseInt(ss1[i++]);
			if (j < m) b = Integer.parseInt(ss2[j++]);
			if (a != b) return a > b ? 1 : -1;
		}
		return 0;
	}

	//myself
	public static int compareVersion1(String v1, String v2) {
		String[] s1 = v1.split("\\.");
		String[] s2 = v2.split("\\.");
		int i = 0;
		int j = 0;
		while (i < s1.length || j < s2.length) {
			int a = 0;
			int b = 0;
			if (i < s1.length) a = Integer.parseInt(s1[i++]);
			if (i < s2.length) b = Integer.parseInt(s2[j++]);
			if (a != b) return a > b ? 1 : -1;
		}
		return 0;
	}
}
