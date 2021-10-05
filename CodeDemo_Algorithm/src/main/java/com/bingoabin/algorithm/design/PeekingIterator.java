package com.bingoabin.algorithm.design;

import java.util.Iterator;

/**
 * @Author: xubin34
 * @Date: 2021/10/5 8:21 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class PeekingIterator implements Iterator<Integer> {
	//Leetcode 284. 窥探迭代器
	//请你设计一个迭代器，除了支持 hasNext 和 next 操作外，还支持 peek 操作。
	// 实现 PeekingIterator 类：
	// PeekingIterator(int[] nums) 使用指定整数数组 nums 初始化迭代器。
	// int next() 返回数组中的下一个元素，并将指针移动到下个元素处。
	// bool hasNext() 如果数组中存在下一个元素，返回 true ；否则，返回 false 。
	// int peek() 返回数组中的下一个元素，但 不 移动指针。
	Iterator<Integer> iter;
	Integer next;

	//初始的4个方法
	public PeekingIterator(Iterator<Integer> iterator) {
		// initialize any member here.
		this.iter = iterator;
		if (iter.hasNext()) next = iter.next();
	}

	// Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
		return next;
	}

	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
		Integer ans = next;
		next = iter.hasNext() ? iter.next() : null;
		return ans;
	}

	@Override
	public boolean hasNext() {
		return next != null;
	}
}
