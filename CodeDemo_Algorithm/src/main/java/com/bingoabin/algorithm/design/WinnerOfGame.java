package com.bingoabin.algorithm.design;

/**
 * @Author: xubin34
 * @Date: 2022/3/22 10:07 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class WinnerOfGame {
    //Leetcode 2038. 如果相邻两个颜色均相同则删除当前颜色
    //示例:输入：colors = "AAABABB"
    //    输出：true
    //    解释：
    //    AAABABB -> AABABB
    //    Alice 先操作。
    //    她删除从左数第二个 'A' ，这也是唯一一个相邻颜色片段都是 'A' 的 'A' 。
    //    现在轮到 Bob 操作。
    //    Bob 无法执行任何操作，因为没有相邻位置都是 'B' 的颜色片段 'B' 。
    //    因此，Alice 获胜，返回 true 。
    //分析：总共有 n个颜色片段排成一列，每个颜色片段要么是'A'要么是'B'。给你一个长度为n的字符串colors，其中colors[i]表示第i个颜色片段的颜色。
    //    Alice 和 Bob 在玩一个游戏，他们 轮流从这个字符串中删除颜色。Alice 先手。
    //    如果一个颜色片段为 'A'且 相邻两个颜色都是颜色 'A'，那么 Alice 可以删除该颜色片段。Alice不可以删除任何颜色'B'片段。
    //    如果一个颜色片段为 'B'且 相邻两个颜色都是颜色 'B'，那么 Bob 可以删除该颜色片段。Bob 不可以删除任何颜色 'A'片段。
    //    Alice 和 Bob 不能从字符串两端删除颜色片段。
    //    如果其中一人无法继续操作，则该玩家 输掉游戏且另一玩家 获胜。
    //    假设 Alice 和 Bob 都采用最优策略，如果 Alice 获胜，请返回true，否则 Bob 获胜，返回false。
    //思路：
    public static void main(String[] args) {
        WinnerOfGame winnerOfGame = new WinnerOfGame();
        String colors = "AAABABB";
        System.out.println(winnerOfGame.winnerOfGame(colors));
    }

    //统计A，B能操作的次数，然后比较次数的大小
    public boolean winnerOfGame(String colors) {
        char[] cs = colors.toCharArray();
        int n = cs.length;
        int a = 0, b = 0;
        for (int i = 1; i < n - 1; i++) {
            if (cs[i] == 'A' && cs[i - 1] == 'A' && cs[i + 1] == 'A') a++;
            if (cs[i] == 'B' && cs[i - 1] == 'B' && cs[i + 1] == 'B') b++;
        }
        return a > b;
    }
}
