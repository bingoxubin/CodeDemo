package com.bingoabin.algorithm.graph;

/**
 * @Author: xubin34
 * @Date: 2021/12/19 9:38 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class FindJudge {
    //Leetcode 997. 找到小镇的法官
    //示例：输入：n = 2, trust = [[1,2]]  输出：2
    //     输入：n = 3, trust = [[1,3],[2,3]]  输出：3
    //     输入：n = 3, trust = [[1,3],[2,3],[3,1]] 输出：-1
    //     输入：n = 3, trust = [[1,2],[2,3]]  输出：-1
    //     输入：n = 4, trust = [[1,3],[1,4],[2,3],[2,4],[4,3]]  输出：3
    //分析：在一个小镇里，按从 1 到 n 为 n 个人进行编号。传言称，这些人中有一个是小镇上的秘密法官。
    //     如果小镇的法官真的存在，那么：
    //     小镇的法官不相信任何人。
    //     每个人（除了小镇法官外）都信任小镇的法官。
    //     只有一个人同时满足条件 1 和条件 2 。
    //     给定数组trust，该数组由信任对 trust[i] = [a, b]组成，表示编号为 a 的人信任编号为 b 的人。
    //     如果小镇存在秘密法官并且可以确定他的身份，请返回该法官的编号。否则，返回 -1。
    //思路：除了法官，其他人都投了法官，法官谁都没投
    public static void main(String[] args) {
        int n = 3;
        int[][] trust = {{1, 3}, {2, 3}};
        FindJudge findJudge = new FindJudge();
        System.out.println(findJudge.findJudge1(n, trust));
        System.out.println(findJudge.findJudge2(n, trust));
    }

    //方式一：创建一个数组，如果投出去-- 如果有人投他++
    public int findJudge1(int n, int[][] trust) {
        if (n == 1) return n;
        int[] res = new int[n + 1];
        //投出去的-- 投进来的 ++
        for (int i = 0; i < trust.length; i++) {
            res[trust[i][0]]--;
            res[trust[i][1]]++;
        }
        //遍历每个人，如果等于n-1，返回那个人就是法官
        for (int i = 0; i < res.length; i++) {
            if (res[i] == n - 1) {
                return i;
            }
        }
        return -1;
    }

    //方式二：图的入度 图的出度
    public int findJudge2(int n, int[][] trust) {
        int[] in = new int[n + 1];
        int[] out = new int[n + 1];
        for (int i = 0; i < trust.length; i++) {
            //入度
            in[trust[i][1]]++;
            //出度
            out[trust[i][0]]++;
        }
        //注意从1开始，如果这种情况1个人，没有人投票，那么如果从0开始返回0，得从第一个人开始
        for (int i = 1; i < in.length; i++) {
            if (in[i] == n - 1 && out[i] == 0) {
                return i;
            }
        }
        return -1;
    }
}
