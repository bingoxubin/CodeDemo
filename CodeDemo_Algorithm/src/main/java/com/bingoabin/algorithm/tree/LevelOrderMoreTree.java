package com.bingoabin.algorithm.tree;

import java.util.*;

/**
 * @Author: xubin34
 * @Date: 2022/4/8 9:20 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class LevelOrderMoreTree {
    //Leetcode 429. N 叉树的层序遍历
    //示例：输入：root = [1,null,3,2,4,null,5,6]
    //     输出：[[1],[3,2,4],[5,6]]
    //分析：给定一个 N 叉树，返回其节点值的层序遍历。（即从左到右，逐层遍历）。
    //     树的序列化输入是用层序遍历，每组子节点都由 null 值分隔（参见示例）。
    //思路：
    public static void main(String[] args) {
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        List<Node> list1 = new ArrayList<>();
        list1.add(node5);
        list1.add(node6);

        Node node3 = new Node(3);
        node3.children = list1;

        Node node2 = new Node(2);
        Node node4 = new Node(4);
        List<Node> list2 = new ArrayList<>();
        list2.add(node3);
        list2.add(node2);
        list2.add(node4);

        Node node1 = new Node(1);
        node1.children = list2;

        LevelOrderMoreTree levelOrderMoreTree = new LevelOrderMoreTree();
        System.out.println(levelOrderMoreTree.levelOrder(node1));
        System.out.println(levelOrderMoreTree.levelOrder1(node1));
    }

    //迭代
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Deque<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            while (size-- > 0) {
                Node node = queue.pollFirst();
                for (Node child : node.children) {
                    queue.addLast(child);
                }
                list.add(node.val);
            }
            res.add(list);
        }
        return res;
    }

    //递归
    int max;
    Map<Integer, List<Integer>> map = new HashMap<>();
    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> levelOrder1(Node root) {
        if (root == null) return ans;
        dfs(root, 0);
        for (int i = 0; i <= max; i++) ans.add(map.get(i));
        return ans;
    }

    void dfs(Node u, int depth) {
        max = Math.max(max, depth);
        List<Integer> list = map.getOrDefault(depth, new ArrayList<>());
        list.add(u.val);
        map.put(depth, list);
        for (Node node : u.children) dfs(node, depth + 1);
    }
}
