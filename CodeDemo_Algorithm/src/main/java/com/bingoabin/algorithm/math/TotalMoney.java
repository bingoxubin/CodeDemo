package com.bingoabin.algorithm.math;

/**
 * @Author: xubin34
 * @Date: 2022/1/16 10:09 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class TotalMoney {
    //Leetcode 1716. 计算力扣银行的钱
    //示例：输入：n = 10
    //     输出：37
    //     解释：第 10 天后，总额为 (1 + 2 + 3 + 4 + 5 + 6 + 7) + (2 + 3 + 4) = 37 。注意到第二个星期一，Hercy 存入 2 块钱。
    //分析：Hercy 想要为购买第一辆车存钱。他 每天 都往力扣银行里存钱。
    //     最开始，他在周一的时候存入 1块钱。从周二到周日，他每天都比前一天多存入 1块钱。在接下来每一个周一，他都会比 前一个周一 多存入 1块钱。
    //     给你n，请你返回在第 n天结束的时候他在力扣银行总共存了多少块钱。
    //思路：
    public static void main(String[] args) {
        TotalMoney totalMoney = new TotalMoney();
        System.out.println(totalMoney.totalMoney(10));
    }

    public int totalMoney(int n) {
        //每周 + 1
        int div = 0;
        //最终结果
        int res = 0;
        //当天的值
        int day = 0;
        for (int i = 1; i <= n; i++) {
            res += div + ++day;
            if (i % 7 == 0) {
                div++;
                day = 0;
            }
        }
        return res;
    }
}
