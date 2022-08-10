package com.bingoabin.test;

import com.bingoabin.utils.ListUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author bingoabin
 * @date 2022/8/10 13:37
 * @Description: 两个list相互比较
 */
public class TestListCompare {
	public static void main(String[] args){
		// List<Integer> listnew = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
		// List<Integer> listold = Arrays.asList(5, 6, 7, 8, 9, 10);
		List<Integer> listnew = new ArrayList<>();
		List<Integer> listold = new ArrayList<>();

		//新增的1 2 3 4
		List<Integer> insert =
				ListUtil.stream(listnew).filter(e -> !listold.contains(e)).collect(Collectors.toList());
		System.out.println("insert" + insert);

		//删掉的9 10
		List<Integer> update =
				ListUtil.stream(listold).filter(e -> !listnew.contains(e)).collect(Collectors.toList());
		System.out.println("update" + update);

		//更新的5 6 7 8
		List<Integer> delete =
				ListUtil.stream(listold).filter(e -> listnew.contains(e)).collect(Collectors.toList());
		System.out.println("delete" + delete);
	}
}
