package com.bingoabin.algorithm.array;

/**
 * @Author: xubin34
 * @Date: 2021/11/8 10:20 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class GetHint {
	//Leetcode 299. 猜数字游戏
	//示例：输入: secret = "1807", guess = "7810"
	//     输出: "1A3B"
	//     解释: 数字和位置都对（公牛）用 '|' 连接，数字猜对位置不对（奶牛）的采用斜体加粗标识。
	//     "1807"
	//       |
	//     "7810"
	//分析：给一个标准字符串，然后猜，猜后的数字进行描述，数字和位置都对的话用*A表示,表示公牛，数字对位置不对的用*B表示，表示母牛
	//思路：
	public static void main(String[] args) {
		String secret = "1807";
		GetHint getHint = new GetHint();
		System.out.println(getHint.getHint(secret, "7810"));
		System.out.println(getHint.getHint1(secret, "7810"));
	}

	public String getHint(String secret, String guess) {
		int bulls = 0;
		int[] cntS = new int[10];
		int[] cntG = new int[10];
		for (int i = 0; i < secret.length(); ++i) {
			if (secret.charAt(i) == guess.charAt(i)) {
				++bulls;
			} else {
				++cntS[secret.charAt(i) - '0'];
				++cntG[guess.charAt(i) - '0'];
			}
		}
		int cows = 0;
		for (int i = 0; i < 10; ++i) {
			cows += Math.min(cntS[i], cntG[i]);
		}
		return Integer.toString(bulls) + "A" + Integer.toString(cows) + "B";
	}

	//自己实现一遍
	public String getHint1(String secret, String guess) {
		//先统计出位置相同  并且数字也相同的个数
		int bulls = 0;
		//如果同一位置上的数字不相等，那么分别用10个数字位进行记录数字个数
		int[] cntS = new int[10];
		int[] cntG = new int[10];
		for (int i = 0; i < secret.length(); i++) {
			if (secret.charAt(i) == guess.charAt(i)) {
				bulls++;
			} else {
				cntS[secret.charAt(i) - '0']++;
				cntG[guess.charAt(i) - '0']++;
			}
		}
		//从两个位置数组中 统计出母牛的个数，也就是相同位置取两个数组的最小值相加，比如secret中有7个1 1个8   guess中有4个1 3个8  那么中和就是有4个+ 1个母牛 ，取最小值
		int cows = 0;
		for(int i = 0;i<10;i++){
			cows += Math.min(cntS[i], cntG[i]);
		}
		return Integer.toString(bulls) + "A" + Integer.toString(cows) + "B";
	}
}
