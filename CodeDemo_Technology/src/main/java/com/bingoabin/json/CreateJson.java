package com.bingoabin.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;

/**
 * @author xubin34
 * @date 2021/7/20 1:32 下午
 */
public class CreateJson {
	public static void main(String[] args) {
		User user = new User(1, "bingoabin", 25, "address", "757661238@qq.com");
		//javabean -> json
		String jsonStr = JSON.toJSONString(user);
		System.out.println("String:" + jsonStr);
		//json -> javabean  User必须要有空参构造器
		User person = JSON.parseObject(jsonStr, User.class);
		System.out.println("bean:" + person.getId() + " " + person.getName() + " " + person.getAge() + " " + person.getAddress() + " " + person.getEmail());

		//json
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("id", 2);
		jsonobj.put("name", "bingo");
		jsonobj.put("age", 25);
		jsonobj.put("address", "zjg");
		jsonobj.put("email", "757661238@qq.com");
		System.out.println("jsonobj:" + jsonobj);

		//hashmap
		HashMap<String, Object> map = new HashMap<>();
		map.put("id", 2);
		map.put("name", "xubin");
		map.put("age", 23);
		map.put("address", "zjgys");
		map.put("email", "757661238@qq.com");
		System.out.println("哈希:" + new JSONObject(map));

		//实体
		User user1 = new User(1, "bingoabin", 25, "address", "757661238@qq.com");
		JSON obj = (JSON) JSON.toJSON(user1);
		System.out.println("实体：" + obj);
		String str = JSONObject.toJSONString(user1);
		System.out.println("实体：" + str);

		//String -> JSONObject
		String user2 = "{\"id\":1,\"name\":\"bin\",\"age\":2,\"address\":\"address\",\"email\":\"757661238@qq.com\"}";
		//JSON字符串转换成JSON对象
		JSONObject json = JSONObject.parseObject(user2);
		System.out.println("string->json:" + json);
	}
}
