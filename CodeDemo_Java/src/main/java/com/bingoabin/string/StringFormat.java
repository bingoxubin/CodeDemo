package com.bingoabin.string;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xubin34
 * @Date: 2022/1/21 4:04 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class StringFormat {
    public static final String TABLE_DELETE_MESSAGE = "%d. 上游表及关联关系被删除：%s";
    public static void main(String[] args){
        int messageNo = 0;
        List<String> tableDeleteMessages = new ArrayList<>();
        tableDeleteMessages.add("zhangsan");
        tableDeleteMessages.add("lisi");
        tableDeleteMessages.add("wangwu");
        String message = String.format(TABLE_DELETE_MESSAGE, ++messageNo, String.join("、", tableDeleteMessages));
        //1. 上游表及关联关系被删除：zhangsan、lisi、wangwu
        System.out.println(message);
    }

    @Test
    public void test(){
        String msg = "xubin %%p XXXXXXX";
        String clientId = "xssss";
        System.out.println(String.format("cancel client '%s'.Error: " +  msg, clientId));
    }
}
