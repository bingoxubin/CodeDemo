package com.bingoabin.technology.bloomfilter;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * @author bingoabin
 * @date 2021/6/13 20:30
 */
//布隆过滤器，主要是实现一个数或者值，是否在一个集合中，可以确认肯定在，或者可能不在的情况，存在一定的误差
public class BloomFilterTest {
	public static void main(String[] args) {
		int total = 1000000;
		//构造一个布隆过滤器
		//通过设置误差率，来进行判断
		BloomFilter<CharSequence> filter = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), total, 0.001);
		//将100万个数放入到布隆过滤器中
		for (int i = 0; i < total; i++) {
			filter.put("" + i);
		}
		//判断不隆过滤器中是否存在0-1010000,计算误差率
		int count = 0;
		for(int i = 0;i<total + 10000;i++){
			if (filter.mightContain("" + i)) {
				count++;
			}
		}
		System.out.println(count);
	}
}
