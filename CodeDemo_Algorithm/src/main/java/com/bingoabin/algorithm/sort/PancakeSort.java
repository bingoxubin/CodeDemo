package com.bingoabin.algorithm.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xubin34
 * @Date: 2022/2/20 7:49 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class PancakeSort {
    //Leetcode 969. 煎饼排序
    //示例：输入：[3,2,4,1]
    //     输出：[4,2,4,3]
    //     解释：
    //     我们执行 4 次煎饼翻转，k 值分别为 4，2，4，和 3。
    //     初始状态 arr = [3, 2, 4, 1]
    //     第一次翻转后（k = 4）：arr = [1, 4, 2, 3]
    //     第二次翻转后（k = 2）：arr = [4, 1, 2, 3]
    //     第三次翻转后（k = 4）：arr = [3, 2, 1, 4]
    //     第四次翻转后（k = 3）：arr = [1, 2, 3, 4]，此时已完成排序。
    //分析：给你一个整数数组 arr ，请使用 煎饼翻转 完成对数组的排序。
    //     一次煎饼翻转的执行过程如下：
    //     选择一个整数 k ，1 <= k <= arr.length
    //     反转子数组 arr[0...k-1]（下标从 0 开始）
    //     例如，arr = [3,2,1,4] ，选择 k = 3 进行一次煎饼翻转，反转子数组 [3,2,1] ，得到 arr = [1,2,3,4] 。
    //     以数组形式返回能使 arr 有序的煎饼翻转操作所对应的 k 值序列。任何将数组排序且翻转次数在10 * arr.length 范围内的有效答案都将被判断为正确。
    //思路：
    public static void main(String[] args) {
        int[] arr = {3, 2, 4, 1};
        PancakeSort pancakeSort = new PancakeSort();
        System.out.println(pancakeSort.pancakeSort(arr));
    }

    //先找到最大的，换到组前面，然后跟最后的位置换一下，把最大的放到了最后了
    public List<Integer> pancakeSort(int[] arr) {
        List<Integer> res = new ArrayList<>();
        for (int len = arr.length; len > 1; len--) {
            //找出剩下的最大值的下标
            int index = 0;
            for (int i = 1; i < len; i++) {
                if (arr[i] >= arr[index]) {
                    index = i;
                }
            }
            if (index == len - 1) {
                continue;
            }
            reverse(arr, index);
            reverse(arr, len - 1);
            res.add(index + 1);
            res.add(len);
        }
        return res;
    }

    public void reverse(int[] arr, int end) {
        int left = 0;
        int right = end;
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }
}
