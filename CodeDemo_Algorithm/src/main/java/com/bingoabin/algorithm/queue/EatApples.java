package com.bingoabin.algorithm.queue;

import java.util.PriorityQueue;

/**
 * @Author: xubin34
 * @Date: 2021/12/24 11:38 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class EatApples {
    //Leetcode 1705. 吃苹果的最大数目
    //示例：输入：apples = [1,2,3,5,2], days = [3,2,1,4,2]  输出：7
    //     解释：你可以吃掉 7 个苹果：
    //     第一天，你吃掉第一天长出来的苹果。
    //     第二天，你吃掉一个第二天长出来的苹果。
    //     第三天，你吃掉一个第二天长出来的苹果。过了这一天，第三天长出来的苹果就已经腐烂了。
    //     第四天到第七天，你吃的都是第四天长出来的苹果。
    //分析：有一棵特殊的苹果树，一连 n 天，每天都可以长出若干个苹果。在第 i 天，树上会长出 apples[i] 个苹果，
    //     这些苹果将会在 days[i] 天后（也就是说，第 i + days[i] 天时）腐烂，变得无法食用。也可能有那么几天，
    //     树上不会长出新的苹果，此时用 apples[i] == 0 且 days[i] == 0 表示。
    //     你打算每天 最多 吃一个苹果来保证营养均衡。注意，你可以在这 n 天之后继续吃苹果。
    //     给你两个长度为 n 的整数数组 days 和 apples ，返回你可以吃掉的苹果的最大数目。
    //思路：
    public static void main(String[] args) {
        int[] apples = {1, 2, 3, 5, 2};
        int[] days = {3, 2, 1, 4, 2};
        EatApples eatApples = new EatApples();
        System.out.println(eatApples.eatenApples(apples, days));
        System.out.println(eatApples.eatenApples1(apples, days));
    }

    public int eatenApples1(int[] apples, int[] days) {
        int res = 0;
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        for (int i = 0; i < apples.length || !queue.isEmpty(); i++) {
            while (!queue.isEmpty()) {
                int[] apple = queue.peek();
                if (apple[1] <= i) {
                    queue.poll();
                } else {
                    break;
                }
            }
            if (i < apples.length && apples[i] > 0) {
                queue.add(new int[]{apples[i], days[i] + i});
            }
            int[] arr = queue.peek();
            if (arr != null && arr[0] > 0) {
                res++;
                arr[0] -= 1;
                if (arr[0] == 0) {
                    queue.poll();
                }
            }
        }
        return res;
    }

    public int eatenApples(int[] apples, int[] days) {
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int n = apples.length, time = 0, ans = 0;
        while (time < n || !q.isEmpty()) {
            if (time < n && apples[time] > 0) q.add(new int[]{time + days[time] - 1, apples[time]});
            while (!q.isEmpty() && q.peek()[0] < time) q.poll();
            if (!q.isEmpty()) {
                int[] cur = q.poll();
                if (--cur[1] > 0 && cur[0] > time) q.add(cur);
                ans++;
            }
            time++;
        }
        return ans;
    }
}
