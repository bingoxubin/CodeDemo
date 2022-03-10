package com.bingoabin.algorithm.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: xubin34
 * @Date: 2022/3/10 9:50 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class NTreePreOrder {
    //Leetcode 589. N 叉树的前序遍历
    //示例：输入：root = [1,null,3,2,4,null,5,6]
    //     输出：[1,3,5,6,2,4]
    //分析：给定一个 n叉树的根节点 root，返回 其节点值的 前序遍历 。
    //     n 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。
    //思路：
    public static void main(String[] args) {
        NNode node5 = new NNode(5);
        NNode node6 = new NNode(6);

        List<NNode> list3 = new ArrayList<>();
        list3.add(node5);
        list3.add(node6);
        NNode node3 = new NNode(3, list3);

        NNode node2 = new NNode(2);
        NNode node4 = new NNode(4);

        List<NNode> list1 = new ArrayList<>();
        list1.add(node3);
        list1.add(node2);
        list1.add(node4);
        NNode node1 = new NNode(1, list1);

        NTreePreOrder nTreePreOrder = new NTreePreOrder();
        System.out.println(nTreePreOrder.preorder(node1));
        System.out.println(nTreePreOrder.preorder_cur(node1));
    }

    //迭代
    public List<Integer> preorder(NNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Deque<NNode> stack = new LinkedList<>();
        stack.addLast(root);
        while (!stack.isEmpty()) {
            NNode node = stack.removeLast();
            res.add(node.val);
            for (int i = node.children.size() - 1; i >= 0; i--) {
                stack.addLast(node.children.get(i));
            }
        }
        return res;
    }

    //递归
    public List<Integer> preorder_cur(NNode root) {
        List<Integer> res = new ArrayList<>();
        ndfs(root, res);
        return res;
    }

    public void ndfs(NNode root, List<Integer> res) {
        if (root == null) return;
        res.add(root.val);
        for (NNode child : root.children) {
            ndfs(child, res);
        }
    }

    //N叉树前序遍历--迭代实现
    public List<Integer> preorder_copy(Node root) {
        List<Integer> result = new ArrayList<Integer>();
        if (root == null) return result;
        Deque<Node> stack = new LinkedList<Node>();
        stack.addLast(root);
        while (!stack.isEmpty()) {
            Node node = stack.removeLast();
            result.add(node.val);
            for (int i = node.children.size() - 1; i >= 0; i--) {
                stack.addLast(node.children.get(i));
            }
        }
        return result;
    }

    //N叉树前序遍历--递归实现
    public List<Integer> preorder_cur_copy(Node root) {
        List<Integer> result = new ArrayList<Integer>();
        dfs(root, result);
        return result;
    }

    public void dfs(Node root, List<Integer> result) {
        if (root == null) return;
        result.add(root.val);
        for (Node child : root.children) {
            dfs(child, result);
        }
    }
}

class NNode {
    public int val;
    public List<NNode> children;

    public NNode() {
    }

    public NNode(int val) {
        this.val = val;
    }

    public NNode(int val, List<NNode> children) {
        this.val = val;
        this.children = children;
    }
}
