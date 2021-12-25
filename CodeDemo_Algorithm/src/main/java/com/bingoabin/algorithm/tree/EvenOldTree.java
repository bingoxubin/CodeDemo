package com.bingoabin.algorithm.tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author: xubin34
 * @Date: 2021/12/25 12:35 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class EvenOldTree {
    //LeetCode 1609. 奇偶树
    //示例：输入：root = [1,10,4,3,null,7,9,12,8,6,null,null,2]
    //     输出：true
    //     解释：每一层的节点值分别是：
    //     0 层：[1]
    //     1 层：[10,4]
    //     2 层：[3,7,9]
    //     3 层：[12,8,6,2]
    //     由于 0 层和 2 层上的节点值都是奇数且严格递增，而 1 层和 3 层上的节点值都是偶数且严格递减，因此这是一棵奇偶树。
    //分析：如果一棵二叉树满足下述几个条件，则可以称为 奇偶树 ：
    //     二叉树根节点所在层下标为 0 ，根的子节点所在层下标为 1 ，根的孙节点所在层下标为 2 ，依此类推。
    //     偶数下标 层上的所有节点的值都是 奇 整数，从左到右按顺序 严格递增
    //     奇数下标 层上的所有节点的值都是 偶 整数，从左到右按顺序 严格递减
    //     给你二叉树的根节点，如果二叉树为 奇偶树 ，则返回 true ，否则返回 false 。
    //思路：
    public static void main(String[] args) {
        TreeNode node12 = new TreeNode(12);
        TreeNode node8 = new TreeNode(8);
        TreeNode node6 = new TreeNode(6);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3, node12, node8);
        TreeNode node7 = new TreeNode(7, node6, null);
        TreeNode node9 = new TreeNode(9, null, node2);
        TreeNode node10 = new TreeNode(10, node3, null);
        TreeNode node4 = new TreeNode(4, node7, node9);
        TreeNode node1 = new TreeNode(1, node10, node4);

        EvenOldTree evenOldTree = new EvenOldTree();
        System.out.println(evenOldTree.isEvenOddTree(node1));
    }

    public boolean isEvenOddTree(TreeNode root) {
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        //最开始的时候是第一层
        int level = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            //判断是否是递增 或者 递减的情况   如果是奇数层应该递增  偶数层递减
            int prev = level % 2 == 1 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                //如果层数的奇偶性  跟 数值的机构性不一致，直接返回false
                if (level % 2 != node.val % 2) {
                    return false;
                }
                //如果是偶数，如果不递减  或者 如果是奇数，不递增  那么也返回false
                if ((level % 2 == 0 && node.val >= prev) || (level % 2 == 1 && node.val <= prev)) {
                    return false;
                }
                //记录上一个数字
                prev = node.val;
                //将数字加入到queue中
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            //层数 加1
            level++;
        }
        return true;
    }
}
