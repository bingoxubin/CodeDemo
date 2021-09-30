package com.bingoabin.test;

import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: xubin34
 * @Date: 2021/9/26 5:19 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class TestHashSet {
	public static final Pattern TBL_NAME_PATTERN = Pattern.compile("^([^.]+)(\\.)(.*)$");

	public static void main(String[] args){
		HashSet<Long> set = new HashSet<>();
		set.forEach(e -> System.out.println(e));
		String dbAndTableName = null;
		Matcher matcher1 = TBL_NAME_PATTERN.matcher(dbAndTableName);
	}
}
