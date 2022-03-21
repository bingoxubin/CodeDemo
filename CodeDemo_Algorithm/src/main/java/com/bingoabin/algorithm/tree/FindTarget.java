package com.bingoabin.algorithm.tree;

import java.util.*;

/**
 * @Author: xubin34
 * @Date: 2022/3/21 9:51 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class FindTarget {
    //Leetcode 653. 两数之和 IV - 输入 BST
    //示例：输入: root = [5,3,6,2,4,null,7], k = 9
    //     输出: true
    //分析：给定一个二叉搜索树 root 和一个目标结果 k，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。
    //思路：
    public static void main(String[] args) {
        TreeNode node2 = new TreeNode(2);
        TreeNode node4 = new TreeNode(4);
        TreeNode node3 = new TreeNode(3);
        node3.left = node2;
        node2.right = node4;

        TreeNode node7 = new TreeNode(7);
        TreeNode node6 = new TreeNode(6);
        node6.right = node7;

        TreeNode node5 = new TreeNode(5);
        node5.left = node3;
        node5.right = node6;

        FindTarget findTarget = new FindTarget();
        System.out.println(findTarget.findTarget(node5, 9));
    }

    //dfs + hashset
    public boolean findTarget1(TreeNode root, int k) {
        Set<Integer> set = new HashSet<>();
        return dfs(root, k, set);
    }

    public boolean dfs(TreeNode root, int k, Set<Integer> set) {
        if (root == null) return false;
        if (set.contains(k - root.val)) return true;
        set.add(root.val);
        return dfs(root.left, k, set) || dfs(root.right, k, set);
    }

    //bfs + hashset
    public boolean findTarget2(TreeNode root, int k) {
        if (root == null) return false;
        Queue<TreeNode> queue = new LinkedList<>();
        HashSet<Integer> set = new HashSet<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (set.contains(k - node.val)) return true;
            set.add(node.val);
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
        return false;
    }

    //双指针
    public boolean findTarget(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        int left = 0;
        int right = list.size() - 1;
        while (left < right) {
            int sum = list.get(left) + list.get(right);
            if (sum == k) {
                return true;
            } else if (sum > k) {
                right--;
            } else {
                left++;
            }
        }
        return false;
    }

    public void inorder(TreeNode root, List<Integer> list) {
        if (root == null) return;
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }
}
