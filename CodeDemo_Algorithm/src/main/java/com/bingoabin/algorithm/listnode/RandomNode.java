package com.bingoabin.algorithm.listnode;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xubin34
 * @Date: 2022/1/16 10:40 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class RandomNode {
    //Leetcode 382. 链表随机节点
    //示例：输入["Solution", "getRandom", "getRandom", "getRandom", "getRandom", "getRandom"]  [[[1, 2, 3]], [], [], [], [], []]
    //     输出 [null, 1, 3, 2, 2, 3]
    //     解释
    //     Solution solution = new Solution([1, 2, 3]);
    //     solution.getRandom(); // 返回 1
    //     solution.getRandom(); // 返回 3
    //     solution.getRandom(); // 返回 2
    //     solution.getRandom(); // 返回 2
    //     solution.getRandom(); // 返回 3
    //     getRandom() 方法应随机返回 1、2、3中的一个，每个元素被返回的概率相等。
    //分析：给你一个单链表，随机选择链表的一个节点，并返回相应的节点值。每个节点 被选中的概率一样 。
    //     实现 Solution 类：
    //     Solution(ListNode head) 使用整数数组初始化对象。
    //     int getRandom() 从链表中随机选择一个节点并返回该节点的值。链表中所有节点被选中的概率相等。
    //思路：方式一：记录所有链表元素
    //     方式二：水塘抽样
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        node1.next = node2;
        node2.next = node3;
        RandomNode randomNode = new RandomNode(node1);
        System.out.println(randomNode.getRandom());
        System.out.println(randomNode.getRandom());
        System.out.println(randomNode.getRandom());

        ListNode node11 = new ListNode(1);
        ListNode node22 = new ListNode(2);
        ListNode node33 = new ListNode(3);
        node11.next = node22;
        node22.next = node33;
        RandomNode2 randomNode2 = new RandomNode2(node11);
        System.out.println(randomNode2.getRandom());
        System.out.println(randomNode2.getRandom());
        System.out.println(randomNode2.getRandom());

    }

    List<Integer> list;
    SecureRandom random;

    //方式一：记录所有节点
    public RandomNode(ListNode node) {
        //将所有节点放到list中，并且生成一个随机数对象，通过list的size范围内生成随机数
        list = new ArrayList<>();
        while (node != null) {
            list.add(node.val);
            node = node.next;
        }
        random = new SecureRandom();
    }

    public int getRandom() {
        return list.get(random.nextInt(list.size()));
    }
}

//方式二：水塘抽样
class RandomNode2 {
    ListNode head;
    SecureRandom random;

    public RandomNode2(ListNode node) {
        head = node;
        random = new SecureRandom();
    }

    public int getRandom() {
        int i = 1;
        int ans = 0;
        for (ListNode node = head; node != null; node = node.next) {
            if (random.nextInt(i) == 0) {
                ans = node.val;
            }
            i++;
        }
        return ans;
    }
}
