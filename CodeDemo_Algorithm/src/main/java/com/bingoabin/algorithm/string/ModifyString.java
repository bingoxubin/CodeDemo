package com.bingoabin.algorithm.string;

/**
 * @Author: xubin34
 * @Date: 2022/1/5 9:48 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class ModifyString {
    //LeetCode 1576. 替换所有的问号
    //示例：输入：s = "?zs"
    //     输出："azs"
    //     解释：该示例共有 25 种解决方案，从 "azs" 到 "yzs" 都是符合题目要求的。只有 "z" 是无效的修改，因为字符串 "zzs" 中有连续重复的两个 'z' 。
    //分析：给你一个仅包含小写英文字母和 '?' 字符的字符串 s，请你将所有的 '?' 转换为若干小写字母，使最终的字符串不包含任何 连续重复 的字符。
    //     注意：你 不能 修改非 '?' 字符。
    //     题目测试用例保证 除 '?' 字符 之外，不存在连续重复的字符。
    //     在完成所有转换（可能无需转换）后返回最终的字符串。如果有多个解决方案，请返回其中任何一个。可以证明，在给定的约束条件下，答案总是存在的。
    //思路：
    public static void main(String[] args) {
        String s = "??yw?ipkj?";
        ModifyString modifyString = new ModifyString();
        System.out.println(modifyString.modifyString(s));
    }

    public String modifyString(String s) {
        //将字符串转成字符数组
        char[] res = s.toCharArray();
        //从头开始遍历
        for (int i = 0; i < res.length; i++) {
            //如果是？  那么拿到前面一个字符 跟后面一个字符
            if (res[i] == '?') {
                char before = i == 0 ? ' ' : res[i - 1];
                char after = i == res.length - 1 ? ' ' : res[i + 1];
                //获取到不等于前面 又 不等于后面的字符，进行赋值res[i]
                char temp = 'a';
                while (temp == before || temp == after) {
                    temp++;
                }
                res[i] = temp;
            }
        }
        //转化格式，拿到结果
        return new String(res);
    }
}
