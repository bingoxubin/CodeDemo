package com.bingoabin.algorithm.string;

/**
 * @Author: xubin34
 * @Date: 2021/10/5 8:03 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class LicenseKeyFormat {
	//Leetcode 482. 密钥格式化
	//示例：输入：S = "2-5g-3-J", K = 2
	//     输出："2-5G-3J"
	//     解释：字符串 S 被分成了 3 个部分，按照前面的规则描述，第一部分的字符可以少于给定的数量，其余部分皆为 2 个字符。
	//分析：将一个字符串格式化，将k个字符通过-分隔，特别地，第一个分组包含的字符个数必须小于等于 K，但至少要包含 1 个字符。
	//思路：将字符串从后面开始进行分隔，每k个插入一个-  最后进行翻转
	public static void main(String[] args) {
		String str = "2-5g-3-J";
		LicenseKeyFormat licenseKeyFormat = new LicenseKeyFormat();
		System.out.println(licenseKeyFormat.licenseKeyFormatting(str, 2));
	}

	public String licenseKeyFormatting(String s, int k) {
		//创建结果集
		StringBuilder res = new StringBuilder();
		//将字符串从后往前进行统计
		for (int i = s.length() - 1, cnt = 0; i >= 0; i--) {
			//如果碰到 - 进行跳过
			if (s.charAt(i) == '-') continue;
			//如果cnt == k 那么插入"-" 并且将cnt重新置为0
			if (k == cnt) {
				cnt = 0;
				res.append("-");
			}
			//将字符  插入到res中
			res.append(s.charAt(i));
			//cnt ++
			cnt++;
		}
		//将结果集进行翻转，进行大写
		return res.reverse().toString().toUpperCase();
	}
}
