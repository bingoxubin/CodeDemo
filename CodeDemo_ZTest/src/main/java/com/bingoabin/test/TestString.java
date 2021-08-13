package com.bingoabin.test;

/**
 * @Author: xubin34
 * @Date: 2021/8/13 11:20 上午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class TestString {
	public static void main(String[] args){
		String path = "猫眼娱乐/行业产品研发部/后台研发组";
		path = path.substring(path.indexOf("-", path.indexOf("-") + 1)).replaceAll("-", "/");
		System.out.println(path);

		// String path = "猫眼娱乐-行业产品研发部-后台研发组";
		// path = path.substring(path.indexOf("-", path.indexOf("-") + 1)).replaceAll("-", "/");
		// System.out.println(path);

		// String path = "猫眼娱乐/行业产品研发部/后台研发组";
		// path = path.replaceAll("-","/").substring(path.indexOf("/"));
		// System.out.println(path);
	}
}
