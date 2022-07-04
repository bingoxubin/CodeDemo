package com.bingoabin.algorithm.string;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bingoabin
 * @date 2022/7/4 10:36
 */
public class ReverseGame {
	//Leetcode 293. 翻转游戏
	//示例：示例：
	//      输入: s = "++++"
	//      输出:
	//      [
	//        "--++",
	//        "+--+",
	//        "++--"
	//      ]
	//分析：你和朋友玩一个叫做「翻转游戏」的游戏，游戏规则：给定一个只有 + 和 - 的字符串。你和朋友轮流将 连续 的两个 "++" 反转成 "--"。 当一方无法进行有效的翻转时便意味着游戏结束，则另一方获胜。
	//      请你写出一个函数，来计算出第一次翻转后，字符串所有的可能状态。
	//思路：
	public static void main(String[] args) {
		ReverseGame reverseGame = new ReverseGame();
		System.out.println(reverseGame.generatePossibleNextMoves("++++"));
	}

	public List<String> generatePossibleNextMoves(String s) {
		List<String> res = new ArrayList<>();
		if (s == null || s.length() == 0) return res;
		for (int i = 0; i < s.length() - 1; i++) {
			if (s.charAt(i) == '+' && s.charAt(i + 1) == '+') {
				StringBuffer buffer = new StringBuffer(s);
				buffer.replace(i, i + 2, "--");
				res.add(buffer.toString());
			}
		}
		return res;
	}
}
