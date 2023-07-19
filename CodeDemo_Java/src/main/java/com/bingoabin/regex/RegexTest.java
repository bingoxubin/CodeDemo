package com.bingoabin.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author bingoabin
 * @date 2023/7/19 14:37
 * @Description:
 */
public class RegexTest {
	public static void main(String[] args){
		String res = "SELECT * FROM student;";
		Pattern pattern = Pattern.compile("(SELECT\\s+.+?\\s+FROM\\s+[^\\s;]+\\s*(WHERE\\s+[^;]+)?;)", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(res);
		if (matcher.find()) {
			System.out.println(matcher.group());
		}
	}
}
