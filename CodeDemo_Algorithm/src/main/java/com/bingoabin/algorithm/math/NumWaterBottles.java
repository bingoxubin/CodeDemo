package com.bingoabin.algorithm.math;

/**
 * @Author: xubin34
 * @Date: 2021/12/17 9:51 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class NumWaterBottles {
    //Leetcode 1518. 换酒问题
    //示例：输入：numBottles = 9, numExchange = 3 输出：13
    //    解释：你可以用 3 个空酒瓶兑换 1 瓶酒。
    //    所以最多能喝到 9 + 3 + 1 = 13 瓶酒。
    //分析：小区便利店正在促销，用 numExchange 个空酒瓶可以兑换一瓶新酒。你购入了 numBottles 瓶酒。如果喝掉了酒瓶中的酒，那么酒瓶就会变成空的。
    //     请你计算 最多 能喝到多少瓶酒。
    //思路：迭代推断的方式
    public static void main(String[] args) {
        NumWaterBottles numWaterBottles = new NumWaterBottles();
        System.out.println(numWaterBottles.numWaterBottles(9, 3));
        System.out.println(numWaterBottles.numWaterBottles1(9, 3));
    }

    //迭代推断的方式
    public int numWaterBottles(int numBottles, int numExchange) {
        //累计能喝到的酒数量
        int res = numBottles;
        while (numBottles >= numExchange) {
            //喝到的酒
            int drink = numBottles / numExchange;
            //累加喝到的酒数量
            res += drink;
            //空瓶数量
            numBottles = drink + numBottles % numExchange;
        }
        return res;
    }

    //自己实现一遍
    public int numWaterBottles1(int numBottles, int numExchange) {
        //一共能喝多少酒
        int res = numBottles;
        while (numBottles >= numExchange) {
            //喝剩下的空瓶能换多少酒
            int drink = numBottles / numExchange;
            //一共喝的酒累加上
            res += drink;
            //下一次 有多少空瓶参与兑换
            numBottles = drink + numBottles % numExchange;
        }
        return res;
    }
}
