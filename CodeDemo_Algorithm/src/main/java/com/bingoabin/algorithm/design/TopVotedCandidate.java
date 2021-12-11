package com.bingoabin.algorithm.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: xubin34
 * @Date: 2021/12/11 9:51 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class TopVotedCandidate {
    //Leetcode 911. 在线选举
    //示例：输入：["TopVotedCandidate", "q", "q", "q", "q", "q", "q"]
    //          [[[0, 1, 1, 0, 0, 1, 0], [0, 5, 10, 15, 20, 25, 30]], [3], [12], [25], [15], [24], [8]]
    //     输出：[null, 0, 1, 1, 0, 0, 1]
    //     解释：
    //     TopVotedCandidate topVotedCandidate = new TopVotedCandidate([0, 1, 1, 0, 0, 1, 0], [0, 5, 10, 15, 20, 25, 30]);
    //     topVotedCandidate.q(3); // 返回 0 ，在时刻 3 ，票数分布为 [0] ，编号为 0 的候选人领先。
    //     topVotedCandidate.q(12); // 返回 1 ，在时刻 12 ，票数分布为 [0,1,1] ，编号为 1 的候选人领先。
    //     topVotedCandidate.q(25); // 返回 1 ，在时刻 25 ，票数分布为 [0,1,1,0,0,1] ，编号为 1 的候选人领先。（在平局的情况下，1 是最近获得投票的候选人）。
    //     topVotedCandidate.q(15); // 返回 0
    //     topVotedCandidate.q(24); // 返回 0
    //     topVotedCandidate.q(8); // 返回 1
    //分析：给你两个整数数组 persons 和 times 。在选举中，第i张票是在时刻为times[i]时投给候选人 persons[i]的。
    //     对于发生在时刻 t 的每个查询，需要找出在t 时刻在选举中领先的候选人的编号。
    //     在t 时刻投出的选票也将被计入我们的查询之中。在平局的情况下，最近获得投票的候选人将会获胜。
    //     实现 TopVotedCandidate 类：
    //     TopVotedCandidate(int[] persons, int[] times) 使用persons 和 times 数组初始化对象。
    //     int q(int t) 根据前面描述的规则，返回在时刻 t 在选举中领先的候选人的编号。
    //思路：
    public static void main(String[] args) {
        int[] persons = {0, 1, 1, 0, 0, 1, 0};
        int[] times = {0, 5, 10, 15, 20, 25, 30};
        TopVotedCandidate topVotedCandidate = new TopVotedCandidate(persons, times);
        System.out.println(topVotedCandidate.q(3));
        System.out.println(topVotedCandidate.q(12));
        System.out.println(topVotedCandidate.q(25));
        System.out.println(topVotedCandidate.q(15));
        System.out.println(topVotedCandidate.q(24));
        System.out.println(topVotedCandidate.q(8));
    }

    //记录在某个时刻 的候选人是谁
    List<int[]> list = new ArrayList<>();

    public TopVotedCandidate(int[] persons, int[] times) {
        //在此时的最高选票数量
        int val = 0;
        //统计某个候选人的选票数
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < times.length; i++) {
            //在当前时刻，给候选人投上一票
            map.put(persons[i], map.getOrDefault(persons[i], 0) + 1);
            //如果当前票数比最大值还大的话，更新val值，并且在当前时刻的指定候选人数组，拼成数组
            if (map.get(persons[i]) >= val) {
                val = map.get(persons[i]);
                list.add(new int[]{times[i], persons[i]});
            }
        }
    }

    //二分方式进行查找
    public int q1(int t) {
        int left = 0;
        int right = list.size() - 1;
        while (left < right) {
            int mid = (right + left + 1) / 2;
            if (list.get(mid)[0] <= t) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return list.get(right)[0] <= t ? list.get(right)[1] : 0;
    }

    //从后往前进行查找
    public int q(int t) {
        for (int i = list.size() - 1; i >= 0; i--) {
            if (t >= list.get(i)[0]) {
                return list.get(i)[1];
            }
        }
        return -1;
    }
}
