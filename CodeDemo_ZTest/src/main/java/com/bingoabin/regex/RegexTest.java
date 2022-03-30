package com.bingoabin.regex;

import org.apache.directory.api.util.Strings;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: xubin34
 * @Date: 2022/3/30 12:42 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class RegexTest {
    @Test
    public void test() {
        String str = "#t_10#";
        Pattern pattern = Pattern.compile("#t_([1-9]\\d*)#");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            System.out.println(matcher.group(1));
        }
    }

    @Test
    public void test1() {
//		String str = "##";
		String str = "asfsf";
//        String str = "#t_1#";
        Pattern pattern = Pattern.compile("#t_([1-9]\\d*)#|##");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            //System.out.println(matcher.group(1));
            if (Strings.isNotEmpty((matcher.group(1)))) {
                System.out.println(Integer.parseInt(matcher.group(1)));
            }
        }else{
            System.out.println("不符合");;
        }
    }
}
