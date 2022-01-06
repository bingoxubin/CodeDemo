package com.bingoabin.algorithm.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author: xubin34
 * @Date: 2022/1/6 9:36 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class SimplifyPath {
    //Leetcode 71. 简化路径
    //示例：输入：path = "/a/./b/../../c/"   输出："/c"
    //分析：给你一个字符串 path ，表示指向某一文件或目录的Unix 风格 绝对路径 （以 '/' 开头），请你将其转化为更加简洁的规范路径。
    //    在 Unix 风格的文件系统中，一个点（.）表示当前目录本身；此外，两个点 （..）表示将目录切换到上一级（指向父目录）；
    //    两者都可以是复杂相对路径的组成部分。任意多个连续的斜杠（即，'//'）都被视为单个斜杠 '/' 。 对于此问题，任何其他格式的点（例如，'...'）均被视为文件/目录名称。
    //    请注意，返回的 规范路径 必须遵循下述格式：
    //    始终以斜杠 '/' 开头。
    //    两个目录名之间必须只有一个斜杠 '/' 。
    //    最后一个目录名（如果存在）不能 以 '/' 结尾。
    //    此外，路径仅包含从根目录到目标文件或目录的路径上的目录（即，不含 '.' 或 '..'）。
    //    返回简化后得到的 规范路径 。
    //思路：栈
    public static void main(String[] args) {
        String path = "/a/./b/../../c/";
        SimplifyPath simplifyPath = new SimplifyPath();
        System.out.println(simplifyPath.simplifyPath(path));
    }

    public String simplifyPath(String path) {
        String[] names = path.split("/");
        Deque<String> stack = new LinkedList<>();
        //如果碰到..  弹出栈
        for (String name : names) {
            if ("..".equals(name)) {
                if (!stack.isEmpty()) {
                    stack.pollLast();
                }
                //否则加入栈
            } else if (name.length() > 0 && !".".equals(name)) {
                stack.offerLast(name);
            }
        }
        //拼接结果
        StringBuffer res = new StringBuffer();
        if (stack.isEmpty()) {
            //如果栈为空，添加/
            res.append("/");
        } else {
            while (!stack.isEmpty()) {
                res.append("/");
                res.append(stack.pollFirst());
            }
        }
        return res.toString();
    }
}
