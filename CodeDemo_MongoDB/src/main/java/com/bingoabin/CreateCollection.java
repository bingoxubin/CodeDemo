package com.bingoabin;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * @author bingoabin
 * @date 2022/8/10 20:38
 * @Description: 创建集合
 */
public class CreateCollection {
	public static void main(String[] args) {
		try{
			//连接mongodb服务端
			final MongoClient mongoClient = new MongoClient("localhost", 27017);
			//连接数据库
			final MongoDatabase mongo = mongoClient.getDatabase("kkb");
			mongo.createCollection("javaCollection");
			System.out.println("集合创建成功");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
