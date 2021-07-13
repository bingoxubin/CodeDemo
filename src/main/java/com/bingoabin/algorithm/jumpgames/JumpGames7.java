package com.bingoabin.algorithm.jumpgames;

import java.util.Arrays;

/**
 * @author xubin03
 * @date 2021/5/24 9:50 上午
 */
public class JumpGames7 {
	//LeetCode 5765. 跳跃游戏 VII  https://leetcode-cn.com/problems/jump-game-vii/
	//样例：输入：s = "011010", minJump = 2, maxJump = 3  输出:true
	//分析：给一个数组，以及步长在minjump和maxjump之间(下标之差），从数组头部开始，能否跳到结尾，数组值为'0'的才能跳
	//思路：用动态规划，进行动态递推
	public static void main(String[] args) {
		String str = "011010";
		System.out.println(canReach(str, 2, 3));
	}

	//遍历字符串数组，看他当前位置是否是0，如果是0，判断该位置前面i - maxjump 到 i - minjump的范围内有多少个1，如果小于 r - l + 1那么里面肯定有0，最终dp[i] = 0否则为1
	//       0 1 1 0 1 0      jump:2~3   假如当前位置为3  那么范围为0-1  代表left - right
	//dp:  1 0 1 1 0 1 0      那么left -right 中有right - left + 1个数组 ，如果有0，表示bdp[right] - bdp[left - 1] < right - left + 1
	//bdp: 0 0 1 2 2 3 3
	public static boolean canReach(String s, int minJump, int maxJump) {
		//长度
		int len = s.length();
		//转化为数组
		char[] chars = s.toCharArray();
		//记录某个点是否能达到 如果能到就是0 ,不能达到就是1
		int[] dp = new int[len + 1];
		//首先设置所有的点都不能达到
		Arrays.fill(dp, 1);
		//但是chars[0] = '0' 那么第一个点一定是能达到的 那么设置为 0
		dp[1] = 0;
		//这个是前缀和的数组，记录的是dp这个数组的前缀和
		int[] bdp = new int[len + 1];
		//第一个一定是0
		bdp[1] = 0;
		//遍历数组
		for (int i = 2; i < len + 1; i++) {
			//选择chars[i - 1]为 0  通过判断这个点的 i - maxJump 到 i - minJump 点的和 是否比 这两个点的距离之和近
			//如果近就说明这里面至少存在一个0 那么就可以通过这个0 来到达chars[i - 1]这个点
			if (chars[i - 1] == '0') {
				if (i - minJump >= 1) {
					int r = i - minJump;
					int l = Math.max(i - maxJump, 1);
					dp[i] = bdp[r] - bdp[l - 1] < r - l + 1 ? 0 : 1;
				}
			}
			//每次都维护前缀和数组,加入 0 或者 1
			bdp[i] += bdp[i - 1] + dp[i];
		}
		System.out.println(Arrays.toString(dp));
		System.out.println(Arrays.toString(bdp));
		return dp[len] == 0;
	}

	public static boolean canReach1(String s, int minJump, int maxJump) {
		int len = s.length();
		char[] chars = s.toCharArray();
		//定义dp数组，表示在某个点能否跳跃到
		int[] dp = new int[len + 1];
		int[] sum = new int[len + 1];
		Arrays.fill(dp, 1);
		dp[1] = 0;
		for (int i = 2; i <= len; i++) {
			if (chars[i - 1] == '0') {
				if (i - minJump >= 1) {
					int left = Math.max(1, i - maxJump);
					int right = i - minJump;
					//前缀和的长度  小于 全部的长度  那么就是其中有1
					dp[i] = sum[right] - sum[left - 1] < right - left + 1 ? 0 : 1;
				}
			}
			sum[i] += sum[i - 1] + dp[i];
		}
		return dp[len] == 0;
	}
}
