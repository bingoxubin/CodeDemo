package com.bingoabin.algorithm.hashmap;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: xubin34
 * @Date: 2021/10/1 10:40 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class DestinationCity {
	//Leetcode 1436. 旅行终点站
	//示例：输入：paths = [["London","New York"],["New York","Lima"],["Lima","Sao Paulo"]]
	//     输出："Sao Paulo"
	//     解释：从 "London" 出发，最后抵达终点站 "Sao Paulo" 。本次旅行的路线是 "London" -> "New York" -> "Lima" -> "Sao Paulo" 。
	//分析：给一个string数组，找到目标城市
	//思路：
	public static void main(String[] args) {
		List<List<String>> res = new ArrayList<List<String>>() {{
			add(new ArrayList<>(Arrays.asList("London", "New York")));
			add(new ArrayList<>(Arrays.asList("New York", "Lima")));
			add(new ArrayList<>(Arrays.asList("Lima", "Sao Paulo")));
		}};
		DestinationCity destinationCity = new DestinationCity();
		System.out.println(destinationCity.destCity1(res));
		System.out.println(destinationCity.destCity2(res));
	}

	//方式一：先将所有初始位置构建一个hashset  然后用目的地去初始位置去匹配，如果初始位置中不包含目的地，那么就是最终目的地
	public String destCity1(List<List<String>> paths) {
		//构建初始城市
		Set<String> startCity = paths.stream().map(e -> e.get(0)).collect(Collectors.toSet());
		for (List<String> path : paths) {
			if (!startCity.contains(path.get(1))) {
				return path.get(1);
			}
		}
		return "";
	}

	//方式二：将初始值构建一个hashmap，然后随便取一个初始值，循环去hashmap中找，直到找不到位置
	public String destCity2(List<List<String>> paths) {
		//将paths 构建成hashmap
		Map<Object, Object> collect = paths.stream().collect(Collectors.toMap(item -> item.get(0), item -> item.get(1)));
		//获取初始值
		String start = paths.get(0).get(0);
		while (collect.containsKey(start)) {
			start = collect.get(start).toString();
		}
		return start;
	}
}
