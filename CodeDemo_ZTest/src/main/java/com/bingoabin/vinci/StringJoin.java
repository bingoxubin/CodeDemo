package com.bingoabin.vinci;

import org.apache.commons.lang3.StringUtils;
import org.apache.parquet.Strings;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xubin34
 * @Date: 2022/3/16 12:11 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class StringJoin {
    public static void main(String[] args) {
        List<String> res = new ArrayList<String>();
        res.add("i");
        res.add("am");
        res.add("zhangsan");
        res.add("you");
        res.add("lisi");

        System.out.println(String.join(",", res));  //jdk1.8
        System.out.println(Strings.join(res, ","));   //org.apache.parquet.Strings
        System.out.println(StringUtils.join(res, ","));  //org.apache.commons.lang3.StringUtils
    }
}
