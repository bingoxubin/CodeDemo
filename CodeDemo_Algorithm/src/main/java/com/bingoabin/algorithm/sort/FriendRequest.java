package com.bingoabin.algorithm.sort;

import java.util.Arrays;

/**
 * @Author: xubin34
 * @Date: 2021/12/27 9:33 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class FriendRequest {
    //Leetcode 825. 适龄的朋友
    //示例：在社交媒体网站上有 n 个用户。给你一个整数数组 ages ，其中 ages[i] 是第 i 个用户的年龄。
    //     如果下述任意一个条件为真，那么用户 x 将不会向用户 y（x != y）发送好友请求：
    //     age[y] <= 0.5 * age[x] + 7
    //     age[y] > age[x]
    //     age[y] > 100 && age[x] < 100
    //     否则，x 将会向 y 发送一条好友请求。
    //     注意，如果 x 向 y 发送一条好友请求，y 不必也向 x 发送一条好友请求。另外，用户不会向自己发送好友请求。
    //     返回在该社交媒体网站上产生的好友请求总数。
    //分析：输入：ages = [16,17,18]
    //     输出：2
    //     解释：产生的好友请求为 17 -> 16 ，18 -> 17 。
    //思路：双指针，只有在一定范围中被邀请好友
    public static void main(String[] args) {
        int[] ages = {16, 17, 18};
        FriendRequest friendRequest = new FriendRequest();
        System.out.println(friendRequest.numFriendRequests(ages));
    }

    public int numFriendRequests(int[] ages) {
        Arrays.sort(ages);
        int len = ages.length, res = 0;
        for (int k = 0, i = 0, j = 0; k < len; k++) {
            //找满足条件的左边界i
            while (i < k && !check(ages[i], ages[k])) {
                i++;
            }
            //初始化右边界
            if (j < k) {
                j = k;
            }
            //寻找右边界
            while (j < len && check(ages[j], ages[k])) {
                j++;
            }
            //找到最终的值，
            if (j > i) {
                res += j - i - 1;
            }
        }
        return res;
    }

    public boolean check(int x, int y) {
        if (y <= 0.5 * x + 7) {
            return false;
        }
        if (y > x) {
            return false;
        }
        if (y > 100 && x < 100) {
            return false;
        }
        return true;
    }
}
