package com.bingoabin;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * @author bingoabin
 * @date 2022/8/10 20:50
 * @Description: 查询文档
 */
public class SearchDocument {
	public static void main(String[] args) {
		//连接mongodb的服务端
		final MongoClient mongoClient = new MongoClient("angel1", 27017);
		//连接数据库
		final MongoDatabase mongo = mongoClient.getDatabase("mongo");
		//连接集合
		final MongoCollection<Document> javaCollection = mongo.getCollection("javaCollection");
		//通过find返回一个迭代器
		final FindIterable<Document> documents = javaCollection.find();
		//获取游标
		final MongoCursor<Document> iterator = documents.iterator();
		while (iterator.hasNext()){
			System.out.println(iterator.next());
		}
	}
}
