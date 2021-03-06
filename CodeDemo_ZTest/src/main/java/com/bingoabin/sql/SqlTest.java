package com.bingoabin.sql;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.directory.api.util.Strings;
import org.junit.Test;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: xubin34
 * @Date: 2022/3/29 5:02 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class SqlTest {
    public static void main(String[] args) {
//        List<String> list = new ArrayList<>();
//        list.add("${werwrwrwerwr} = 1");
//        String reloadConfig = "#t_0#${werwrwrwerwr} = 1";
//        System.out.println(Arrays.toString(reloadConfig.split(String.join("|", list))));

        test1();

        String str1 = "sfdadfafsafsafsadffas\n" +
                "#t_1#\n" +
                "asfsfasfasf   safsfasaf   asfsaf\n" +
                "#t_2#\n" +
                "safaaagfdsgdsgfdsafdasf\n" +
                "#t_3#\n" +
                "asfsadfdsaff\n" +
                "##";



        test(str1, 4);
        System.out.println("=================================");

        String str = "safsafsafdsafsafsaf";
        test(str, 1);

        System.out.println("=================================");
        String str2 = "#t_0#\n" +
                "safsafsafdsafsafsaf\n" +
                "##";
        test(str2, 1);

        System.out.println("=================================");
        String str3 = "asfasfafsafsafasf\n" +
                "##";
        test(str3, 1);

        System.out.println("=================================");
        String str4 = "#t_1#\n" +
                "sdfsdafasfsafsadf\n" +
                "##";
        test(str4, 2);

        System.out.println("=================================");
        String str5 = "#t_1#\n" +
                "safasfasfdsafsafsafsf";
        test(str5, 2);

        System.out.println("=================================");
        String str6 = "#t_3#\n" +
                "sfafasfafasfasfa\n" +
                "#t_1#\n" +
                "safasfasfdsafsafsafsf"+
                "##";
        test(str6, 4);

        System.out.println("=================================");
        String str7 = "#t_0#\n" +
                "safsfsadfsdfsf";

    }
    public static final Pattern RELOAD_SQL_MARKER = Pattern.compile("#t_([0-9]\\d*)#|##");

    @Test
    public static void test2(){
        String str1 = "sfdadfafsafsafsadffas\n" +
                "#t_1#\n" +
                "asfsfasfasf   safsfasaf   asfsaf\n" +
                "#t_2#\n" +
                "safaaagfdsgdsgfdsafdasf\n" +
                "#t_3#\n" +
                "asfsadfdsaff\n" +
                "##";
        Matcher matcher = RELOAD_SQL_MARKER.matcher(str1);
        if (matcher.find()) {
            int count = matcher.groupCount();
            for(int i = 1;i<= count;i++){
                String group = matcher.group(i);
                System.out.println(group);
            }
        }
    }

    @Test
    public static void test1(){
        String[] reloadMarkers = {"#t_0#","","#t_1#","##"};
        // 简单校验
        for (String reloadMarker : reloadMarkers) {
            Matcher matcher = RELOAD_SQL_MARKER.matcher(reloadMarker);
            if (matcher.find()) {
                String group = matcher.group(1);
                if (StringUtils.isNotEmpty(group)) {
                    System.out.println("true");
                }
            } else {
                System.out.println("error");
            }
            }
    }

    public static void test(String str, int n) {
        //取到分隔符
        List<String> markers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            markers.add("#t_" + i + "#");
        }
        markers.add("##");

        String[] reloadSqlSplit = str.split(String.join("|", markers));
        String[] reloadSql = reloadSqlSplit;
        if(reloadSqlSplit.length > 1 && Strings.isEmpty(reloadSqlSplit[0])){
            reloadSql = new String[reloadSqlSplit.length - 1];
            int index = 0;
            for(int i = 1;i<reloadSqlSplit.length;i++){
                reloadSql[index++] = reloadSqlSplit[i];
            }
        }
        String[] reloadMarkers = str.split(String.join("|", Arrays.asList(reloadSql)));
        for (String reloadMarker : reloadMarkers) {
//            if(reloadMarker.)
        }

        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < reloadSql.length; i++) {
            if (CollectionUtils.isEmpty(Arrays.asList(reloadMarkers)) || Strings.isEmpty(reloadMarkers[i])) {
                map.put("#t_0#", reloadSqlSplit[i]);
            } else {
                map.put(reloadMarkers[i], reloadSql[i]);
            }
        }

        for (String key : map.keySet()) {
            System.out.println(key + " : " + map.get(key));
        }
    }
}
