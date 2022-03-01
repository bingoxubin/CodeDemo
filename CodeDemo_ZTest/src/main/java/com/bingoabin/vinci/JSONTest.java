package com.bingoabin.vinci;

import com.bingoabin.domain.People;
import com.bingoabin.utils.JsonUtil;
import org.junit.Test;

/**
 * @Author: xubin34
 * @Date: 2022/3/1 6:05 下午
 * @CopyRight: sankuai.com
 * @Description:
 */
public class JSONTest {
    public static void main(String[] args){
        JSONTest jsonTest = new JSONTest();
        jsonTest.testParse();
    }

    //测试 JsonUtil.parse
    @Test
    public void testParse(){
        String json = "{\n" +
                "\"id\":1,\n" +
                "\"name\":\"xubin\",\n" +
                "\"age\":20\n" +
                "}";
        People p = JsonUtil.parse(json, People.class);
        System.out.println(p.toString());
    }

    @Test
    public void testToString(){
        People p = new People();
        p.setId(1);
        p.setName("xb");
        p.setAge(20);
        String s = JsonUtil.toJSONString(p);
        System.out.println(s);
    }

    @Test
    public void testToJsonString(){
        People p = new People();
        p.setId(1);
        p.setName("xb");
        p.setAge(20);
        String s = JsonUtil.JSON.toJSONString(p);
        System.out.println(s);
    }

    @Test
    public void testToParse(){
        String json = "{\n" +
                "\"id\":1,\n" +
                "\"name\":\"xubin\",\n" +
                "\"age\":20\n" +
                "}";
        JsonUtil.JSONObject parse = JsonUtil.parse(json);
        String s = JsonUtil.JSONObject.toJSONString(parse);
        System.out.println(s);
    }
}
