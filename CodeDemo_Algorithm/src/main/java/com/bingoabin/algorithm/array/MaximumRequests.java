package com.bingoabin.algorithm.array;

/**
 * @Author: xubin34
 * @Date: 2022/3/1 9:50 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class MaximumRequests {
    //Leetcode 1601. 最多可达成的换楼请求数目
    //示例：输入：n = 5, requests = [[0,1],[1,0],[0,1],[1,2],[2,0],[3,4]]
    //     输出：5
    //     解释：请求列表如下：
    //     从楼 0 离开的员工为 x 和 y ，且他们都想要搬到楼 1 。
    //     从楼 1 离开的员工为 a 和 b ，且他们分别想要搬到楼 2 和 0 。
    //     从楼 2 离开的员工为 z ，且他想要搬到楼 0 。
    //     从楼 3 离开的员工为 c ，且他想要搬到楼 4 。
    //     没有员工从楼 4 离开。
    //     我们可以让 x 和 b 交换他们的楼，以满足他们的请求。
    //     我们可以让 y，a 和 z 三人在三栋楼间交换位置，满足他们的要求。
    //     所以最多可以满足 5 个请求。
    //分析：我们有n栋楼，编号从0到n - 1。每栋楼有若干员工。由于现在是换楼的季节，部分员工想要换一栋楼居住。
    //     给你一个数组 requests，其中requests[i] = [fromi, toi]，表示一个员工请求从编号为fromi的楼搬到编号为toi的楼。
    //     一开始所有楼都是满的，所以从请求列表中选出的若干个请求是可行的需要满足 每栋楼员工净变化为 0。意思是每栋楼 离开的员工数目 等于该楼 搬入的员工数数目。比方说n = 3且两个员工要离开楼0，一个员工要离开楼1，一个员工要离开楼 2，如果该请求列表可行，应该要有两个员工搬入楼0，一个员工搬入楼1，一个员工搬入楼2。
    //     请你从原请求列表中选出若干个请求，使得它们是一个可行的请求列表，并返回所有可行列表中最大请求数目。
    //思路：
    public static void main(String[] args) {
        int[][] requests = {{0, 1}, {1, 0}, {0, 1}, {1, 2}, {2, 0}, {3, 4}};
        MaximumRequests maximumRequests = new MaximumRequests();
        System.out.println(maximumRequests.maximumRequests(5, requests));
    }

    int[][] rs;

    public int maximumRequests(int n, int[][] requests) {
        rs = requests;
        int m = rs.length, ans = 0;
        for (int i = 0; i < (1 << m); i++) {
            int cnt = getCnt(i);
            if (cnt <= ans) continue;
            if (check(i)) ans = cnt;
        }
        return ans;
    }

    boolean check(int s) {
        int[] cnt = new int[20];
        int sum = 0;
        for (int i = 0; i < 16; i++) {
            if (((s >> i) & 1) == 1) {
                int a = rs[i][0], b = rs[i][1];
                if (++cnt[a] == 1) sum++;
                if (--cnt[b] == 0) sum--;
            }
        }
        return sum == 0;
    }

    int getCnt(int s) {
        int ans = 0;
        for (int i = s; i > 0; i -= (i & -i)) ans++;
        return ans;
    }
}
