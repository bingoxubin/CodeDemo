package com.bingoabin.test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.Arrays;
import java.util.List;

/**
 * @author bingoabin
 * @date 2022/8/16 9:36
 * @Description: 测试分页
 */
public class TestPageList {
	public static void main(String[] args){
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17);
		// PageList<Integer> res = new PageList<>(10, 1, 17, list);
		// System.out.println(res);

		PageHelper.startPage(1, 10);
		PageInfo<Integer> res1 = new PageInfo<>(list);
		System.out.println(res1);
	}
}
