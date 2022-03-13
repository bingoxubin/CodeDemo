package com.bingoabin.algorithm.math;

/**
 * @Author: xubin34
 * @Date: 2022/3/13 3:40 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class ValidUtf8 {
    //Leetcode 393. UTF-8 编码验证
    //示例：输入：data = [197,130,1]
    //     输出：true
    //     解释：数据表示字节序列:11000101 10000010 00000001。
    //     这是有效的 utf-8 编码，为一个 2 字节字符，跟着一个 1 字节字符。
    //分析：给定一个表示数据的整数数组data，返回它是否为有效的 UTF-8 编码。
    //     UTF-8 中的一个字符可能的长度为 1 到 4 字节，遵循以下的规则：
    //     对于 1 字节的字符，字节的第一位设为 0 ，后面 7 位为这个符号的 unicode 码。
    //     对于 n 字节的字符 (n > 1)，第一个字节的前 n 位都设为1，第 n+1 位设为 0 ，后面字节的前两位一律设为 10 。剩下的没有提及的二进制位，全部为这个符号的 unicode 码。
    //     这是 UTF-8 编码的工作方式：
    //     Char. number range  |        UTF-8 octet sequence
    //      (hexadecimal)    |              (binary)
    //        --------------------+---------------------------------------------
    //        0000 0000-0000 007F | 0xxxxxxx
    //        0000 0080-0000 07FF | 110xxxxx 10xxxxxx
    //        0000 0800-0000 FFFF | 1110xxxx 10xxxxxx 10xxxxxx
    //        0001 0000-0010 FFFF | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
    //     注意：输入是整数数组。只有每个整数的 最低 8 个有效位 用来存储数据。这意味着每个整数只表示 1 字节的数据。
    //思路：
    public static void main(String[] args){
        int[] data = {197, 130, 1};
        ValidUtf8 validUtf8 = new ValidUtf8();
        System.out.println(validUtf8.validUtf8(data));
    }

    public boolean validUtf8(int[] data) {
        int n = data.length;
        for (int i = 0; i < n; ) {
            int t = data[i], j = 7;
            while (j >= 0 && (((t >> j) & 1) == 1)) j--;
            int cnt = 7 - j;
            if (cnt == 1 || cnt > 4) return false;
            if (i + cnt - 1 >= n) return false;
            for (int k = i + 1; k < i + cnt; k++) {
                if ((((data[k] >> 7) & 1) == 1) && (((data[k] >> 6) & 1) == 0)) continue;
                return false;
            }
            if (cnt == 0) i++;
            else i += cnt;
        }
        return true;
    }
}
