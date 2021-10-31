package com.bingoabin.algorithm.math;

/**
 * @Author: xubin34
 * @Date: 2021/10/30 7:19 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class DistinceCrossing {
	//Leetcode 335. 路径交叉
	//示例：输入：distance = [2,1,1,2]  输出：true
	//分析：给你一个整数数组 distance 。从 X-Y 平面上的点(0,0)开始，先向北移动 distance[0] 米，然后向西移动 distance[1] 米，
	//     向南移动 distance[2] 米，向东移动 distance[3] 米，持续移动。也就是说，每次移动后你的方位会发生逆时针变化。
	//     判断你所经过的路径是否相交。如果相交，返回 true ；否则，返回 false 。
	//思路：进行枚举方案 https://leetcode-cn.com/problems/self-crossing/solution/lu-jing-jiao-cha-by-leetcode-solution-dekx/
	//                https://leetcode-cn.com/problems/self-crossing/solution/gong-shui-san-xie-fen-qing-kuang-tao-lun-zdrb/
	public static void main(String[] args) {
		int[] arr = {2, 1, 1, 2};
		DistinceCrossing distinceCrossing = new DistinceCrossing();
		System.out.println(distinceCrossing.isSelfCrossing(arr));
	}

	public boolean isSelfCrossing(int[] d) {
		int len = d.length;
		if (len < 4) return false;
		for (int i = 3; i < len; i++) {
			//第一种相交情况
			if (d[i] >= d[i - 2] && d[i - 1] <= d[i - 3]) {
				return true;
			}
			//第二种相交情况
			if (i >= 4 && d[i - 1] == d[i - 3] && d[i] + d[i - 4] >= d[i - 2]) {
				return true;
			}
			//第三种相交情况
			if (i >= 5 && d[i - 1] <= d[i - 3] && d[i - 2] > d[i - 4] && d[i] + d[i - 4] >= d[i - 2] && d[i - 1] + d[i - 5] >= d[i - 3]) {
				return true;
			}
		}
		return false;
	}
}
