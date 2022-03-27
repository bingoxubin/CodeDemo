package com.bingoabin.algorithm.math;

import java.util.Arrays;

/**
 * @Author: xubin34
 * @Date: 2022/3/27 1:19 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class MissingRolls {
    //Leetcode 2028. 找出缺失的观测数据
    //示例：输入：rolls = [3,2,4,3], mean = 4, n = 2
    //     输出：[6,6]
    //     解释：所有 n + m 次投掷的平均值是 (3 + 2 + 4 + 3 + 6 + 6) / 6 = 4 。
    //     输入：rolls = [1,2,3,4], mean = 6, n = 4
    //     输出：[]
    //     解释：无论丢失的 4 次数据是什么，平均值都不可能是 6 。
    //分析：现有一份 n + m次投掷单个 六面 骰子的观测数据，骰子的每个面从 1 到 6 编号。观测数据中缺失了 n 份，你手上只拿到剩余m 次投掷的数据。幸好你有之前计算过的这 n + m 次投掷数据的 平均值 。
    //     给你一个长度为 m 的整数数组 rolls ，其中rolls[i] 是第 i 次观测的值。同时给你两个整数 mean 和 n 。
    //     返回一个长度为 n 的数组，包含所有缺失的观测数据，且满足这 n + m 次投掷的 平均值 是 mean 。如果存在多组符合要求的答案，只需要返回其中任意一组即可。如果不存在答案，返回一个空数组。
    //     k个数字的 平均值 为这些数字求和后再除以k 。
    //     注意 mean 是一个整数，所以 n + m 次投掷的总和需要被n + m整除。
    //思路：
    public static void main(String[] args) {
        int[] rolls = {3, 2, 4, 3};
        int mean = 4;
        int n = 2;
        MissingRolls missingRolls = new MissingRolls();
        System.out.println(Arrays.toString(missingRolls.missingRolls(rolls, mean, n)));
    }

    public int[] missingRolls(int[] rolls, int mean, int n) {
        int m = rolls.length;
        int sum = mean * (m + n);
        for (int roll : rolls) sum -= roll;
        //此时sum 是剩下n个数的总和
        if (sum < n || sum > 6 * n) return new int[0];
        //新建一个数组
        int[] res = new int[n];
        Arrays.fill(res, sum / n);
        if (sum > sum / n * n) {
            int div = sum - sum / n * n;
            int index = 0;
            while (div-- > 0) {
                res[index++]++;
            }
        }
        return res;
    }
}
