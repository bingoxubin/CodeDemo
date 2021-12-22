package com.bingoabin.algorithm.sort;

import java.util.Arrays;

/**
 * @Author: xubin34
 * @Date: 2021/12/20 12:59 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class FindRadius {
    //Leetcode 475. 供暖器
    //示例：输入: houses = [1,2,3], heaters = [2]   输出: 1  解释: 仅在位置2上有一个供暖器。如果我们将加热半径设为1，那么所有房屋就都能得到供暖。
    //     输入: houses = [1,2,3,4], heaters = [1,4]  输出: 1   解释: 在位置1, 4上有两个供暖器。我们需要将加热半径设为1，这样所有房屋就都能得到供暖。
    //     输入：houses = [1,5], heaters = [2]  输出：3
    //分析：冬季已经来临。你的任务是设计一个有固定加热半径的供暖器向所有房屋供暖。
    //     在加热器的加热半径范围内的每个房屋都可以获得供暖。
    //     现在，给出位于一条水平线上的房屋houses 和供暖器heaters 的位置，请你找出并返回可以覆盖所有房屋的最小加热半径。
    //     说明：所有供暖器都遵循你的半径标准，加热的半径也一样。
    //思路：
    public static void main(String[] args) {
        int[] houses = {1, 2, 3};
        int[] heaters = {2};
        FindRadius findRadius = new FindRadius();
        System.out.println(findRadius.findRadius(houses, heaters));
        System.out.println(findRadius.findRadius1(houses, heaters));
        System.out.println(findRadius.findRadius2(houses, heaters));
    }

    //排序 二分查找
    public int findRadius1(int[] houses, int[] heaters) {
        int ans = 0;
        Arrays.sort(heaters);
        for (int house : houses) {
            int i = binarySearch(heaters, house);
            int j = i + 1;
            int leftDistance = i < 0 ? Integer.MAX_VALUE : house - heaters[i];
            int rightDistance = j >= heaters.length ? Integer.MAX_VALUE : heaters[j] - house;
            int curDistance = Math.min(leftDistance, rightDistance);
            ans = Math.max(ans, curDistance);
        }
        return ans;
    }

    public int binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        if (nums[left] > target) {
            return -1;
        }
        while (left < right) {
            int mid = (right - left + 1) / 2 + left;
            if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return left;
    }

    //排序  双指针
    public int findRadius2(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int ans = 0;
        for (int i = 0, j = 0; i < houses.length; i++) {
            int curDistance = Math.abs(houses[i] - heaters[j]);
            while (j < heaters.length - 1 && Math.abs(houses[i] - heaters[j]) >= Math.abs(houses[i] - heaters[j + 1])) {
                j++;
                curDistance = Math.min(curDistance, Math.abs(houses[i] - heaters[j]));
            }
            ans = Math.max(ans, curDistance);
        }
        return ans;
    }

    //排序 双指针 贪心
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int i = 0, j = 0, ans = 0; // 双指针
        while (i < houses.length) {
            int minDistance = 0;
            if (j == heaters.length - 1) {
                minDistance = Math.abs(heaters[j] - houses[i++]);
            } else {
                int distance1 = Math.abs(heaters[j] - houses[i]);
                int distance2 = Math.abs(heaters[j + 1] - houses[i]);
                if (distance1 >= distance2) j++;
                else { // 当前j位置是给i位置供暖的最优点
                    minDistance = distance1;
                    i++;
                }
            }
            ans = Math.max(ans, minDistance);
        }
        return ans;
    }
}
