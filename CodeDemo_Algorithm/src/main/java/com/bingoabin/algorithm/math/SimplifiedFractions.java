package com.bingoabin.algorithm.math;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xubin34
 * @Date: 2022/2/10 9:43 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class SimplifiedFractions {
    //Leetcode 1447. 最简分数
    //示例：输入：n = 4
    //     输出：["1/2","1/3","1/4","2/3","3/4"]
    //     解释："2/4" 不是最简分数，因为它可以化简为 "1/2" 。
    //分析：给你一个整数 n ，请你返回所有 0 到 1 之间（不包括 0 和 1）满足分母小于等于  n 的 最简 分数 。分数可以以 任意 顺序返回。
    //思路：
    public static void main(String[] args) {
        SimplifiedFractions simplifiedFractions = new SimplifiedFractions();
        System.out.println(simplifiedFractions.simplifiedFractions(4));
    }

    public List<String> simplifiedFractions(int n) {
        List<String> res = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (gcd(i, j) == 1) {
                    res.add(i + "/" + j);
                }
            }
        }
        return res;
    }

    //更相减损法
    public int gcd(int a, int b) {
        while (true) {
            if (a > b) {
                a -= b;
            } else if (a < b) {
                b -= a;
            } else {
                return a;
            }
        }
    }
}
