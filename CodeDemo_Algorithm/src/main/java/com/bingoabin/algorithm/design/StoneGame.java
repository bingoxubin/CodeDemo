package com.bingoabin.algorithm.design;

/**
 * @Author: xubin34
 * @Date: 2022/1/20 9:51 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class StoneGame {
    //Leetcode 2029. 石子游戏 IX
    //示例：输入：stones = [2,1]
    //     输出：true
    //     解释：游戏进行如下：
    //     - 回合 1：Alice 可以移除任意一个石子。
    //     - 回合 2：Bob 移除剩下的石子。
    //     已移除的石子的值总和为 1 + 2 = 3 且可以被 3 整除。因此，Bob 输，Alice 获胜。
    //分析：Alice 和 Bob 再次设计了一款新的石子游戏。现有一行 n 个石子，每个石子都有一个关联的数字表示它的价值。给你一个整数数组 stones ，其中 stones[i] 是第 i 个石子的价值。
    //     Alice 和 Bob 轮流进行自己的回合，Alice 先手。每一回合，玩家需要从 stones中移除任一石子。
    //     如果玩家移除石子后，导致 所有已移除石子 的价值总和 可以被 3 整除，那么该玩家就 输掉游戏 。
    //     如果不满足上一条，且移除后没有任何剩余的石子，那么 Bob 将会直接获胜（即便是在 Alice 的回合）。
    //     假设两位玩家均采用最佳 决策。如果 Alice 获胜，返回 true ；如果 Bob 获胜，返回 false 。
    //思路：
    public static void main(String[] args) {
        int[] stones = {2, 1};
        StoneGame stoneGame = new StoneGame();
        System.out.println(stoneGame.stoneGameIX(stones));
    }

    public boolean stoneGameIX(int[] stones) {
        int[] cnt = new int[3];
        //先统计石头数除以3的余数
        for (int stone : stones) {
            cnt[stone % 3]++;
        }
        //如果石头数是3的倍数的个数为偶数
        if (cnt[0] % 2 == 0) {
            return cnt[1] > 0 && cnt[2] > 0;
        }
        //如果石头数是3的倍数的个数为奇数
        return Math.abs(cnt[1] - cnt[2]) > 2;
    }
}
