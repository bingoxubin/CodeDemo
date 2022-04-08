package com.bingoabin.sql;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: xubin34
 * @Date: 2022/4/7 9:27 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class SqlRegex {

    public static final Pattern RELOAD_SQL_MARKER = Pattern.compile("#t_([0-9]\\d*)#|##");

    public static void main(String[] args) {
        String str1 =
                "#t_0#" +
                "#t_1#\n" +
                "asfsfasfasf   safsfasaf   asfsaf\n" +
                "#t_3#\n" +
                "safaaagfdsgdsgfdsafdasf\n" +
                "#t_2#\n" +
                "asfsadfdsaff\n" +
                "##";
        Matcher matcher = RELOAD_SQL_MARKER.matcher(str1);
        List<String> res = new ArrayList<>();
        while (matcher.find()) {
            String group = matcher.group();
            String group1 = matcher.group(1);
            System.out.println(group1);
            res.add(group);
        }
        String[] split = str1.split(String.join("|", res));
        System.out.println(Arrays.toString(split));
    }
}
