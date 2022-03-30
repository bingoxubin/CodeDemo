package com.bingoabin.sql;

import org.apache.commons.collections.CollectionUtils;
import org.apache.directory.api.util.Strings;

import java.util.*;

/**
 * @Author: xubin34
 * @Date: 2022/3/29 5:02 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class SqlTest {
    public static void main(String[] args) {

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
                "##";
        test(str6, 4);
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
