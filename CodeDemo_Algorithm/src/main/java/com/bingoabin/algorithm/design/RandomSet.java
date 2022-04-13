package com.bingoabin.algorithm.design;

import java.util.*;

/**
 * @author bingoabin
 * @date 2022/4/13 11:38
 */
public class RandomSet {
	//Leetcode 380. O(1) 时间插入、删除和获取随机元素
	//示例：输入
	//      ["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]
	//      [[], [1], [2], [2], [], [1], [2], []]
	//      输出
	//      [null, true, false, true, 2, true, false, 2]
	//      解释
	//      RandomizedSet randomizedSet = new RandomizedSet();
	//      randomizedSet.insert(1); // 向集合中插入 1 。返回 true 表示 1 被成功地插入。
	//      randomizedSet.remove(2); // 返回 false ，表示集合中不存在 2 。
	//      randomizedSet.insert(2); // 向集合中插入 2 。返回 true 。集合现在包含 [1,2] 。
	//      randomizedSet.getRandom(); // getRandom 应随机返回 1 或 2 。
	//      randomizedSet.remove(1); // 从集合中移除 1 ，返回 true 。集合现在包含 [2] 。
	//      randomizedSet.insert(2); // 2 已在集合中，所以返回 false 。
	//      randomizedSet.getRandom(); // 由于 2 是集合中唯一的数字，getRandom 总是返回 2 。
	//分析：实现RandomizedSet 类：
	//      RandomizedSet() 初始化 RandomizedSet 对象
	//      bool insert(int val) 当元素 val 不存在时，向集合中插入该项，并返回 true ；否则，返回 false 。
	//      bool remove(int val) 当元素 val 存在时，从集合中移除该项，并返回 true ；否则，返回 false 。
	//      int getRandom() 随机返回现有集合中的一项（测试用例保证调用此方法时集合中至少存在一个元素）。每个元素应该有 相同的概率 被返回。
	//      你必须实现类的所有函数，并满足每个函数的 平均 时间复杂度为 O(1) 。
	//思路：
	public static void main(String[] args){
		//方式一 用set
		RandomSet randomSet = new RandomSet();
		System.out.println(randomSet.insert(1));
		System.out.println(randomSet.remove(2));
		System.out.println(randomSet.insert(2));
		System.out.println(randomSet.getRandom());
		System.out.println(randomSet.remove(1));
		System.out.println(randomSet.insert(2));
		System.out.println(randomSet.getRandom());

		//方式二 用hashmap + 数组
		RandomizedSet randomizedSet = new RandomizedSet();
		System.out.println(randomizedSet.insert(1));
		System.out.println(randomizedSet.remove(2));
		System.out.println(randomizedSet.insert(2));
		System.out.println(randomizedSet.getRandom());
		System.out.println(randomizedSet.remove(1));
		System.out.println(randomizedSet.insert(2));
		System.out.println(randomizedSet.getRandom());
	}

	Set<Integer> set;

	//初始化 RandomizedSet 对象
	public RandomSet(){
		set = new HashSet<>();
	}

	//bool insert(int val) 当元素 val 不存在时，向集合中插入该项，并返回 true ；否则，返回 false
	public boolean insert(int val){
		if(set.contains(val)) return false;
		set.add(val);
		return true;
	}

	//bool remove(int val) 当元素 val 存在时，从集合中移除该项，并返回 true ；否则，返回 false 。
	public boolean remove(int val){
		if(!set.contains(val)) return false;
		set.remove(val);
		return true;
	}

	//int getRandom() 随机返回现有集合中的一项（测试用例保证调用此方法时集合中至少存在一个元素）。每个元素应该有 相同的概率 被返回。
	public int getRandom(){
		int size = set.size();
		int random = (int) (size * Math.random());
		int index = 0;
		for (Integer num : set) {
			if (random == index) {
				return num;
			}
			index++;
		}
		return 0;
	}
}

class RandomizedSet{
	Map<Integer, Integer> map;
	Random random;
	int[] nums;
	int index = -1;

	//初始化 RandomizedSet 对象
	public RandomizedSet(){
		map = new HashMap<>();
		random = new Random();
		nums = new int[200010];
	}

	//bool insert(int val) 当元素 val 不存在时，向集合中插入该项，并返回 true ；否则，返回 false
	public boolean insert(int val){
		if(map.containsKey(val)) return false;
		nums[++index] = val;
		map.put(val, index);
		return true;
	}

	//bool remove(int val) 当元素 val 存在时，从集合中移除该项，并返回 true ；否则，返回 false 。
	public boolean remove(int val){
		if(!map.containsKey(val)) return false;
		//从map中移除
		Integer remove = map.remove(val);
		//如果移除的不是最后一个,将最后一个map中的放到删除的位置
		if(remove != index) map.put(nums[index], remove);
		//数组也调整下 赋值  并且长度-1
		nums[remove] = nums[index--];
		return true;
	}

	//int getRandom() 随机返回现有集合中的一项（测试用例保证调用此方法时集合中至少存在一个元素）。每个元素应该有 相同的概率 被返回。
	public int getRandom(){
		return nums[random.nextInt(index + 1)];
	}
}
