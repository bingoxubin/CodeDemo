package com.bingoabin.algorithm.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: xubin34
 * @Date: 2022/3/12 1:17 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class NTreePostOrder {
    //Leetcode 590. N 叉树的后序遍历
    //示例：输入：root = [1,null,3,2,4,null,5,6]
    //     输出：[5,6,3,2,4,1]
    //分析：给定一个 n叉树的根节点root，返回 其节点值的 后序遍历 。
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

        NTreePostOrder nTreePostOrder = new NTreePostOrder();
        System.out.println(nTreePostOrder.postorder_iter(node1));
        System.out.println(nTreePostOrder.postorder_cur(node1));
    }

    //迭代
    public List<Integer> postorder_iter(NNode root) {
        List<Integer> ans = new ArrayList<>();
        Deque<NNode> stack = new LinkedList<>();
        if (root == null) return ans;
        stack.push(root);
        while (!stack.isEmpty()) {
            NNode node = stack.pop();
            ans.add(0, node.val);
            for (NNode temp : node.children) {
                stack.push(temp);
            }
        }
        return ans;
    }

    //递归
    List<Integer> result;

    public List<Integer> postorder_cur(NNode root) {
        result = new ArrayList<>();
        if (root == null) return result;
        ndfs(root);
        return result;
    }

    public void ndfs(NNode root) {
        if (root == null) return;
        for (NNode node : root.children) {
            ndfs(node);
        }
        result.add(root.val);
    }

    //递归
    List<Integer> res;

    public List<Integer> postorder1(Node root) {
        res = new ArrayList<Integer>();
        if (root == null) return res;
        dfs(root);
        return res;
    }

    public void dfs(Node root) {
        if (root == null) return;
        for (Node node : root.children) dfs(node);
        res.add(root.val);
    }

    //迭代
    public List<Integer> postorder(Node root) {
        List<Integer> ans = new LinkedList<Integer>();
        Deque<Node> stack = new LinkedList<Node>();
        if (root == null) return ans;
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            ans.add(0, node.val);
            for (Node temp : node.children) {
                stack.push(temp);
            }
        }
        return ans;
    }
}
