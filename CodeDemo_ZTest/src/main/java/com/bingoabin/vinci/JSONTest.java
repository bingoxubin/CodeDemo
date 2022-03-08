package com.bingoabin.vinci;

import com.bingoabin.domain.People;
import com.bingoabin.utils.JsonUtil;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.sf.json.JsonConfig;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
        JsonConfig jsonConfig = new JsonConfig();
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

    @Test
    public void testJsonFilter(){
        People p = new People();
        p.setId(1);
        p.setName("xb");
        p.setAge(20);

        Gson gson = new GsonBuilder().setExclusionStrategies(new SetterExclusionStrategy(new String[]{"id"})).create();
        System.out.println(gson.toJson(p));
    }

    @Test
    public void testJsonExcludeFields(){
        People p1 = new People();
        p1.setId(1);
        p1.setName("xb");
        p1.setAge(20);

        People p2 = new People();
        p2.setId(2);
        p2.setName("xb");
        p2.setAge(21);

        People p3 = new People();
        p3.setId(3);
        p3.setName("xb");
        p3.setAge(22);

        List<People> peoples = new ArrayList<>();
        peoples.add(p1);
        peoples.add(p2);
        peoples.add(p3);
        String s1 = JsonUtil.toJSONString(peoples);
        String s = JsonUtil.toJSONExcludeFieldString(peoples, new String[]{"ss", "name"});
        System.out.println(s);
    }

    private static class SetterExclusionStrategy implements ExclusionStrategy {
        private String[] fields;

        public SetterExclusionStrategy(String[] fields) {
            this.fields = fields;

        }

        @Override
        public boolean shouldSkipClass(Class<?> arg0) {
            return false;
        }

        /**
         * 过滤字段的方法
         */
        @Override
        public boolean shouldSkipField(FieldAttributes f) {
            if (fields != null) {
                for (String name : fields) {
                    if (f.getName().equals(name)) {
                        /** true 代表此字段要过滤 */
                        return true;
                    }
                }
            }
            return false;
        }
    }
}
