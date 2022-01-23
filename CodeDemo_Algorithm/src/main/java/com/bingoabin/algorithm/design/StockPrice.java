package com.bingoabin.algorithm.design;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Author: xubin34
 * @Date: 2022/1/23 1:37 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class StockPrice {
    //Leetcode 2034. 股票价格波动
    //示例：输入：
    //     ["StockPrice", "update", "update", "current", "maximum", "update", "maximum", "update", "minimum"]
    //     [[], [1, 10], [2, 5], [], [], [1, 3], [], [4, 2], []]
    //     输出：
    //     [null, null, null, 5, 10, null, 5, null, 2]
    //     解释：
    //     StockPrice stockPrice = new StockPrice();
    //     stockPrice.update(1, 10); // 时间戳为 [1] ，对应的股票价格为 [10] 。
    //     stockPrice.update(2, 5);  // 时间戳为 [1,2] ，对应的股票价格为 [10,5] 。
    //     stockPrice.current();     // 返回 5 ，最新时间戳为 2 ，对应价格为 5 。
    //     stockPrice.maximum();     // 返回 10 ，最高价格的时间戳为 1 ，价格为 10 。
    //     stockPrice.update(1, 3);  // 之前时间戳为 1 的价格错误，价格更新为 3 。
    //                               // 时间戳为 [1,2] ，对应股票价格为 [3,5] 。
    //     stockPrice.maximum();     // 返回 5 ，更正后最高价格为 5 。
    //     stockPrice.update(4, 2);  // 时间戳为 [1,2,4] ，对应价格为 [3,5,2] 。
    //     stockPrice.minimum();     // 返回 2 ，最低价格时间戳为 4 ，价格为 2 。
    //分析：给你一支股票价格的数据流。数据流中每一条记录包含一个 时间戳和该时间点股票对应的 价格。
    //     不巧的是，由于股票市场内在的波动性，股票价格记录可能不是按时间顺序到来的。某些情况下，有的记录可能是错的。如果两个有相同时间戳的记录出现在数据流中，前一条记录视为错误记录，后出现的记录 更正前一条错误的记录。
    //     请你设计一个算法，实现：
    //     更新 股票在某一时间戳的股票价格，如果有之前同一时间戳的价格，这一操作将更正之前的错误价格。
    //     找到当前记录里 最新股票价格。最新股票价格定义为时间戳最晚的股票价格。
    //     找到当前记录里股票的 最高价格。
    //     找到当前记录里股票的 最低价格。
    //     请你实现StockPrice类：
    //     StockPrice()初始化对象，当前无股票价格记录。
    //     void update(int timestamp, int price)在时间点 timestamp更新股票价格为 price。
    //     int current()返回股票 最新价格。
    //     int maximum()返回股票 最高价格。
    //     int minimum()返回股票 最低价格。
    //思路：
    public static void main(String[] args) {
        StockPrice stockPrice = new StockPrice();
        stockPrice.update(1, 10);
        stockPrice.update(2, 5);
        System.out.println(stockPrice.current());
        System.out.println(stockPrice.maximum());
        stockPrice.update(1, 3);
        System.out.println(stockPrice.maximum());
        stockPrice.update(4, 2);
        System.out.println(stockPrice.minimum());
    }

    //最大的时间
    int maxTime;
    //记录key：时间  value：股票价格
    Map<Integer, Integer> map;
    //记录key：股票价格  value:此价格的个数
    TreeMap<Integer, Integer> treeMap;

    public StockPrice() {
        //初始化
        maxTime = 0;
        map = new HashMap<>();
        treeMap = new TreeMap<>();
    }

    public void update(int timestamp, int price) {
        //更新最大的时间
        maxTime = Math.max(timestamp, maxTime);
        //如果存在当前时间，进行更新，将存在老的treeMap中的个数减1
        if (map.containsKey(timestamp)) {
            int oldPrice = map.get(timestamp);
            int cnt = treeMap.get(oldPrice);
            if (cnt == 1) {
                treeMap.remove(oldPrice);
            } else {
                treeMap.put(oldPrice, cnt - 1);
            }
        }
        //更新新的时间，价格
        map.put(timestamp, price);
        treeMap.put(price, treeMap.getOrDefault(price, 0) + 1);
    }

    public int current() {
        return map.get(maxTime);
    }

    public int maximum() {
        return treeMap.lastKey();
    }

    public int minimum() {
        return treeMap.firstKey();
    }
}
