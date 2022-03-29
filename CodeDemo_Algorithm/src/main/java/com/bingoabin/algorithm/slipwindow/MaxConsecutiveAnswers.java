package com.bingoabin.algorithm.slipwindow;

/**
 * @Author: xubin34
 * @Date: 2022/3/29 9:45 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class MaxConsecutiveAnswers {
    //Leetcode 2024. 考试的最大困扰度
    //示例：输入：answerKey = "TTFF", k = 2
    //     输出：4
    //     解释：我们可以将两个 'F' 都变为 'T' ，得到 answerKey = "TTTT" 。
    //     总共有四个连续的 'T' 。
    //
    //     输入：answerKey = "TTFTTFTT", k = 1
    //     输出：5
    //     解释：我们可以将第一个 'F' 换成 'T' ，得到 answerKey = "TTTTTFTT" 。
    //     或者我们可以将第二个 'F' 换成 'T' ，得到 answerKey = "TTFTTTTT" 。
    //     两种情况下，都有五个连续的 'T' 。
    //分析：一位老师正在出一场由 n道判断题构成的考试，每道题的答案为 true （用 'T' 表示）或者 false （用 'F'表示）。老师想增加学生对自己做出答案的不确定性，
    //    方法是最大化有 连续相同结果的题数。（也就是连续出现 true 或者连续出现 false）。
    //    给你一个字符串answerKey，其中answerKey[i]是第 i个问题的正确结果。除此以外，还给你一个整数 k，表示你能进行以下操作的最多次数：
    //    每次操作中，将问题的正确答案改为'T' 或者'F'（也就是将 answerKey[i] 改为'T'或者'F'）。
    //    请你返回在不超过 k次操作的情况下，最大连续 'T'或者 'F'的数目。
    //思路：
    public static void main(String[] args) {
        String answerKey = "TTFTTFTT";
        MaxConsecutiveAnswers maxConsecutiveAnswers = new MaxConsecutiveAnswers();
        System.out.println(maxConsecutiveAnswers.maxConsecutiveAnswers(answerKey, 1));
        System.out.println(maxConsecutiveAnswers.maxConsecutiveAnswers2(answerKey, 1));
    }

    String s;
    int n, k;

    public int maxConsecutiveAnswers(String answerKey, int _k) {
        s = answerKey;
        n = s.length();
        k = _k;
        return Math.max(getCnt('T'), getCnt('F'));
    }

    int getCnt(char c) {
        int ans = 0;
        for (int i = 0, j = 0, cnt = 0; i < n; i++) {
            if (s.charAt(i) == c) cnt++;
            while (cnt > k) {
                if (s.charAt(j) == c) cnt--;
                j++;
            }
            ans = Math.max(ans, i - j + 1);
        }
        return ans;
    }

    public int maxConsecutiveAnswers2(String answerKey, int k) {
        return Math.max(maxConsecutiveChar(answerKey, k, 'T'), maxConsecutiveChar(answerKey, k, 'F'));
    }

    public int maxConsecutiveChar(String answerKey, int k, char ch) {
        int n = answerKey.length();
        int ans = 0;
        for (int left = 0, right = 0, sum = 0; right < n; right++) {
            sum += answerKey.charAt(right) != ch ? 1 : 0;
            while (sum > k) {
                sum -= answerKey.charAt(left++) != ch ? 1 : 0;
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }
}
