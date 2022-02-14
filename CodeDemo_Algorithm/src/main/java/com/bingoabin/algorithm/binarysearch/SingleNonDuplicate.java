package com.bingoabin.algorithm.binarysearch;

/**
 * @Author: xubin34
 * @Date: 2022/2/14 9:35 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class SingleNonDuplicate {
    //Leetcode 540. 有序数组中的单一元素
    //示例：给你一个仅由整数组成的有序数组，其中每个元素都会出现两次，唯有一个数只会出现一次。
    //     请你找出并返回只出现一次的那个数。
    //     你设计的解决方案必须满足 O(log n) 时间复杂度和 O(1) 空间复杂度。
    //分析：输入: nums =  [3,3,7,7,10,11,11]
    //     输出: 10
    //思路：
    public static void main(String[] args) {
        int[] nums = {3, 3, 7, 7, 10, 11, 11};
        SingleNonDuplicate singleNonDuplicate = new SingleNonDuplicate();
        System.out.println(singleNonDuplicate.singleNonDuplicate(nums));
    }

    public int singleNonDuplicate1(int[] nums) {
        int len = nums.length;
        int left = 0;
        int right = len - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] == nums[mid ^ 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[right];
    }

    public int singleNonDuplicate(int[] nums) {
        int n = nums.length;
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (mid % 2 == 0) {
                if (mid + 1 < n && nums[mid] == nums[mid + 1]) l = mid + 1;
                else r = mid;
            } else {
                if (mid - 1 >= 0 && nums[mid - 1] == nums[mid]) l = mid + 1;
                else r = mid;
            }
        }
        return nums[r];
    }
}
