package com.bingoabin.algorithm.doublepoints;

import java.util.Arrays;

/**
 * @Author: xubin34
 * @Date: 2022/1/29 12:23 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class NumberOfWeakCharacters {
    //Leetcode 1996. 游戏中弱角色的数量
    //示例：输入：properties = [[5,5],[6,3],[3,6]]
    //     输出：0
    //     解释：不存在攻击和防御都严格高于其他角色的角色。
    //
    //     输入：properties = [[2,2],[3,3]]
    //     输出：1
    //     解释：第一个角色是弱角色，因为第二个角色的攻击和防御严格大于该角色。
    //
    //     输入：properties = [[1,5],[10,4],[4,3]]
    //     输出：1
    //     解释：第三个角色是弱角色，因为第二个角色的攻击和防御严格大于该角色。
    //分析：你正在参加一个多角色游戏，每个角色都有两个主要属性：攻击 和 防御 。给你一个二维整数数组 properties ，其中 properties[i] = [attacki, defensei] 表示游戏中第 i 个角色的属性。
    //     如果存在一个其他角色的攻击和防御等级 都严格高于 该角色的攻击和防御等级，则认为该角色为 弱角色 。更正式地，如果认为角色 i 弱于 存在的另一个角色 j ，那么 attackj > attacki 且 defensej > defensei 。
    //     返回 弱角色 的数量。
    //思路：
    public static void main(String[] args) {
        int[][] ps = {{1, 5}, {10, 4}, {4, 3}};
        NumberOfWeakCharacters numberOfWeakCharacters = new NumberOfWeakCharacters();
        System.out.println(numberOfWeakCharacters.numberOfWeakCharacters(ps));
    }

    public int numberOfWeakCharacters(int[][] ps) {
        int n = ps.length, ans = 0;
        Arrays.sort(ps, (a, b) -> {
            if (a[0] != b[0]) return b[0] - a[0];
            return b[1] - a[1];
        });
        for (int i = 0, max = ps[0][1]; i < n; ) {
            int j = i;
            while (j < n && ps[j][0] == ps[i][0]) {
                if (i != 0 && ps[j][1] < max) ans++;
                j++;
            }
            max = Math.max(max, ps[i][1]);
            i = j;
        }
        return ans;
    }
}
