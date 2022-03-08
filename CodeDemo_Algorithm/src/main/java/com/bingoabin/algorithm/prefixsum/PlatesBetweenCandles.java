package com.bingoabin.algorithm.prefixsum;

import java.util.Arrays;

/**
 * @Author: xubin34
 * @Date: 2022/3/8 10:00 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class PlatesBetweenCandles {
    //Leetcode 2055. 蜡烛之间的盘子
    //示例：输入：s = "**|**|***|", queries = [[2,5],[5,9]]
    //     输出：[2,3]
    //     解释：
    //     - queries[0] 有两个盘子在蜡烛之间。
    //     - queries[1] 有三个盘子在蜡烛之间。
    //分析：给你一个长桌子，桌子上盘子和蜡烛排成一列。给你一个下标从 0开始的字符串s，它只包含字符'*' 和'|'，其中'*'表示一个 盘子，'|'表示一支蜡烛。
    //     同时给你一个下标从 0开始的二维整数数组queries，其中queries[i] = [lefti, righti]表示 子字符串s[lefti...righti]（包含左右端点的字符）。
    //     对于每个查询，你需要找到 子字符串中在 两支蜡烛之间的盘子的 数目。如果一个盘子在 子字符串中左边和右边 都至少有一支蜡烛，那么这个盘子满足在 两支蜡烛之间。
    //     比方说，s = "||**||**|*"，查询[3, 8]，表示的是子字符串"*||**|"。子字符串中在两支蜡烛之间的盘子数目为2，子字符串中右边两个盘子在它们左边和右边 都 至少有一支蜡烛。
    //     请你返回一个整数数组answer，其中answer[i]是第i个查询的答案。
    //思路：
    public static void main(String[] args) {
        String s = "**|**|***|";
        int[][] queries = {{2, 5}, {5, 9}};
        PlatesBetweenCandles platesBetweenCandles = new PlatesBetweenCandles();
        System.out.println(Arrays.toString(platesBetweenCandles.platesBetweenCandles(s, queries)));
    }

    public int[] platesBetweenCandles(String s, int[][] qs) {
        char[] cs = s.toCharArray();
        int n = cs.length, m = qs.length;
        int[] l = new int[n], r = new int[n];
        int[] sum = new int[n + 1];
        for (int i = 0, j = n - 1, p = -1, q = -1; i < n; i++, j--) {
            if (cs[i] == '|') p = i;
            if (cs[j] == '|') q = j;
            l[i] = p;
            r[j] = q;
            sum[i + 1] = sum[i] + (cs[i] == '*' ? 1 : 0);
        }
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            int a = qs[i][0], b = qs[i][1];
            int c = r[a], d = l[b];
            if (c != -1 && c <= d) ans[i] = sum[d + 1] - sum[c];
        }
        return ans;
    }
}
