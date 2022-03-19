package com.bingoabin.algorithm.tree;

import java.util.*;

/**
 * @Author: xubin34
 * @Date: 2022/3/19 2:10 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class Tree2String {
    //Leetcode 606. 根据二叉树创建字符串
    //示例：输入: 二叉树: [1,2,3,4]
    //            1
    //          /   \
    //         2     3
    //        /
    //       4
    //
    //     输出: "1(2(4))(3)"
    //          解释: 原本将是“1(2(4)())(3())”，
    //          在你省略所有不必要的空括号对之后，
    //          它将是“1(2(4))(3)”。
    //分析：你需要采用前序遍历的方式，将一个二叉树转换成一个由括号和整数组成的字符串。
    //     空节点则用一对空括号 "()" 表示。而且你需要省略所有不影响字符串与原始二叉树之间的一对一映射关系的空括号对。
    //思路：
    public static void main(String[] args) {
        TreeNode node4 = new TreeNode(4);
        TreeNode node2 = new TreeNode(2);
        node2.left = node4;
        TreeNode node3 = new TreeNode(3);
        TreeNode node1 = new TreeNode(1);
        node1.left = node2;
        node1.right = node3;

        Tree2String tree2String = new Tree2String();
        System.out.println(tree2String.tree2str(node1));
        System.out.println(tree2String.tree2str1(node1));
    }

    //dfs
    public String tree2str1(TreeNode t) {
        if (t == null) return "";
        if (t.left == null && t.right == null) return t.val + "";
        if (t.right == null) return t.val + "(" + tree2str(t.left) + ")";
        return t.val + "(" + tree2str(t.left) + ")(" + tree2str(t.right) + ")";
    }

    //bfs
    public String tree2str(TreeNode t) {
        if (t == null)
            return "";
        Stack<TreeNode> stack = new Stack<>();
        stack.push(t);
        Set<TreeNode> visited = new HashSet<>();
        StringBuilder s = new StringBuilder();
        while (!stack.isEmpty()) {
            t = stack.peek();
            if (visited.contains(t)) {
                stack.pop();
                s.append(")");
            } else {
                visited.add(t);
                s.append("(" + t.val);
                if (t.left == null && t.right != null)
                    s.append("()");
                if (t.right != null)
                    stack.push(t.right);
                if (t.left != null)
                    stack.push(t.left);
            }
        }
        return s.substring(1, s.length() - 1);
    }

    //递归
    StringBuilder sb = new StringBuilder();

    public String tree2str2(TreeNode root) {
        dfs(root);
        return sb.substring(1, sb.length() - 1);
    }

    void dfs(TreeNode root) {
        sb.append("(");
        sb.append(root.val);
        if (root.left != null) dfs(root.left);
        else if (root.right != null) sb.append("()");
        if (root.right != null) dfs(root.right);
        sb.append(")");
    }

    //迭代
    public String tree2str3(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        Deque<Object[]> d = new ArrayDeque<>();
        d.addLast(new Object[]{0, root});
        while (!d.isEmpty()) {
            Object[] poll = d.pollLast();
            int loc = (Integer) poll[0];
            TreeNode t = (TreeNode) poll[1];
            if (loc == 0) {
                sb.append("(");
                sb.append(t.val);
                d.addLast(new Object[]{1, t});
            } else if (loc == 1) {
                d.addLast(new Object[]{2, t});
                if (t.right != null) d.addLast(new Object[]{0, t.right});
                if (t.left != null) d.addLast(new Object[]{0, t.left});
                else if (t.right != null) sb.append("()");
            } else if (loc == 2) {
                sb.append(")");
            }
        }
        return sb.substring(1, sb.length() - 1);
    }
}
