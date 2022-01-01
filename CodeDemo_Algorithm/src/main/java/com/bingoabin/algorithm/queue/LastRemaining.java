package com.bingoabin.algorithm.queue;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xubin34
 * @Date: 2022/1/2 1:14 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class LastRemaining {
    //Leetcode 390. 消除游戏
    //示例：输入:
    //     n = 9,
    //     1 2 3 4 5 6 7 8 9
    //     2 4 6 8
    //     2 6
    //     6
    //     输出:
    //     6
    //分析：给定一个从1 到 n 排序的整数列表。
    //     首先，从左到右，从第一个数字开始，每隔一个数字进行删除，直到列表的末尾。
    //     第二步，在剩下的数字中，从右到左，从倒数第一个数字开始，每隔一个数字进行删除，直到列表开头。
    //     我们不断重复这两步，从左到右和从右到左交替进行，直到只剩下一个数字。
    //     返回长度为 n 的列表中，最后剩下的数字。
    //思路：
    public static void main(String[] args) {
        LastRemaining lastRemaining = new LastRemaining();
        System.out.println(lastRemaining.lastRemaining1(9));
        System.out.println(lastRemaining.lastRemaining2(9));
    }

    //方式一：模拟删除情况  超时
    public int lastRemaining1(int n) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }
        boolean reverse = false;
        while (list.size() > 1) {
            list.removeAll(dele(list, reverse));
            reverse = !reverse;
        }
        return list.get(0);
    }

    public List<Integer> dele(List<Integer> list, boolean reverse) {
        List<Integer> dels = new ArrayList<>();
        if (reverse) {
            for (int i = list.size() - 1; i >= 0; i = i - 2) {
                dels.add(list.get(i));
            }
        } else {
            for (int i = 0; i < list.size(); i = i + 2) {
                dels.add(list.get(i));
            }
        }
        return dels;
    }

    //方式二：
    public int lastRemaining2(int n) {
        if (n <= 1) {
            return n;
        }
        int gc = 1;//公差
        int first = 1;//第一个数
        boolean reverse = false;
        boolean odd = n % 2 == 1;
        while (n != 1) {
            if (!reverse || odd) {
                first += gc;
            }
            reverse = !reverse;
            n >>= 1;
            gc <<= 1;
            odd = n % 2 == 1;
        }

        return first;
    }
}
