package com.bingoabin.algorithm.design;

/**
 * @Author: xubin34
 * @Date: 2021/11/25 12:38 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class PoorPig {
	//Leetcode 458. 可怜的小猪
	//示例：输入：buckets = 1000, minutesToDie = 15, minutesToTest = 60 输出：5
	//     输入：buckets = 4, minutesToDie = 15, minutesToTest = 15  输出：2
	//分析：有 buckets 桶液体，其中 正好 有一桶含有毒药，其余装的都是水。它们从外观看起来都一样。为了弄清楚哪只水桶含有毒药，你可以喂一些猪喝，通过观察猪是否会死进行判断。不幸的是，你只有 minutesToTest 分钟时间来确定哪桶液体是有毒的。
	//     喂猪的规则如下：
	//     选择若干活猪进行喂养
	//     可以允许小猪同时饮用任意数量的桶中的水，并且该过程不需要时间。
	//     小猪喝完水后，必须有 minutesToDie 分钟的冷却时间。在这段时间里，你只能观察，而不允许继续喂猪。
	//     过了 minutesToDie 分钟后，所有喝到毒药的猪都会死去，其他所有猪都会活下来。
	//     重复这一过程，直到时间用完。
	//     给你桶的数目 buckets ，minutesToDie 和 minutesToTest ，返回在规定时间内判断哪个桶有毒所需的 最小 猪数。
	//思路：这道题初看的时候，很多人会纠结：到底需要多少只小猪，而每只小猪又应该具体如何喝水才能判断出哪只水桶有***？
	//     这道题最开始不要去关注细节，去想到底应该怎么喂水。而是应该先思考在考察哪方面的问题，数组、链表、二叉树还是数学？那么仔细思考就能得出结论，本质上在考察数学中的 进制 问题。
	//     举例说明：
	//     假设：总时间 minutesToTest = 60，死亡时间 minutesToDie = 15，pow(x, y) 表示 x 的 y 次方，ceil(x)表示 x 向上取整
	//     当前有 11 只小猪，最多可以喝 times = minutesToTest / minutesToDie = 4 次水
	//     最多可以喝 44 次水，能够携带 base = times + 1 = 5 个的信息量，也就是（便于理解从 00 开始）：
	//     (1) 喝 00 号死去，00 号桶水有毒
	//     (2) 喝 11 号死去，11 号桶水有毒
	//     (3) 喝 22 号死去，22 号桶水有毒
	//     (4) 喝 33 号死去，33 号桶水有毒
	//     (5) 喝了上述所有水依然活蹦乱跳，44 号桶水有毒
	//     结论是 11 只小猪最多能够验证 55 桶水中哪只水桶含有***，当 buckets ≤ 5 时，answer = 1
	//     那么 22 只小猪可以验证的范围最多到多少呢？我们把每只小猪携带的信息量看成是 base进制数，22 只小猪的信息量就是 pow(base, 2) = pow(5, 2) = 25，所以当 5 ≤ buckets ≤ 25时，anwser = 2
	//     那么可以得到公式关系：pow(base, ans) ≥ buckets，取对数后即为：ans ≥ log(buckets) / log(base)，因为 ans 为整数，所以 ans = ceil(log(buckets) / log(base))
	//     https://leetcode-cn.com/problems/poor-pigs/solution/hua-jie-suan-fa-458-ke-lian-de-xiao-zhu-by-guanpen/
	public static void main(String[] args) {
		PoorPig poorPig = new PoorPig();
		System.out.println(poorPig.poorPigs(4, 15, 15));
	}

	public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
		int times = minutesToTest / minutesToDie;
		int base = times + 1;
		// base ^ ans >= buckets
		// ans >= log(buckets) / log(base)
		double temp = Math.log(buckets) / Math.log(base);
		int ans = (int) Math.ceil(temp);  //小数向上取整  16.5 ceil后就是17
		return ans;
	}
}
