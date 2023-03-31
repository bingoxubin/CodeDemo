package com.bingo.third

import java.util
import com.alibaba.fastjson.{JSON, JSONException, JSONObject}
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
import org.apache.spark.streaming.{Durations, StreamingContext}
import redis.clients.jedis.Jedis

object StreamWithStaticDataJoin3 {
	def main(args: Array[String]): Unit = {
		//设置日志等级
		Logger.getLogger("org").setLevel(Level.WARN)
		//Kafka 参数
		val kafkaParams = Map[String, Object](
			"bootstrap.servers" -> "node01:9092,node02:9092,node03:9092",
			"key.deserializer" -> classOf[StringDeserializer],
			"value.deserializer" -> classOf[StringDeserializer],
			"auto.offset.reset" -> "latest",
			"enable.auto.commit" -> (true: java.lang.Boolean),
			"group.id" -> "testGroup3")

		//spark环境
		val sparkConf = new SparkConf().setAppName(this.getClass.getSimpleName.replace("$", "")).setMaster("local[3]")
		val ssc = new StreamingContext(sparkConf, Durations.seconds(5))

		/** 1) 半静态数据: 用户基础信息,在Redis中 */
		/** HMSET user_1 userID "user_1" name "name_1" address "address_1" */
		/** HMSET user_2 userID "user_2" name "name_2" address "address_2" */


		/** 2) 流式数据: 用户发的tweet数据 */
		/** 数据示例：
		  * eventTime：事件时间、retweetCount：转推数、language：语言、userID：用户ID、favoriteCount：点赞数、id：事件ID
		  * {"eventTime": "2018-11-05 10:04:00", "retweetCount": 1, "language": "chinese", "userID": "user_1", "favoriteCount": 1, "id": 4909846540155641457} */

		val kafkaDStream = KafkaUtils.createDirectStream[String, String](
			ssc,
			LocationStrategies.PreferConsistent,
			ConsumerStrategies.Subscribe[String, String](Set("test"), kafkaParams)
		).map(item => parseJson(item.value())).map(item => {
			val userID = item.getString("userID")
			val eventTime = item.getString("eventTime")
			val language = item.getString("language")
			val favoriteCount = item.getInteger("favoriteCount")
			val retweetCount = item.getInteger("retweetCount")
			(userID, (userID, eventTime, language, favoriteCount, retweetCount))
		})

		/** 3) 流与半静态数据做Join (RDD Join 方式) */
		val result: DStream[(String, ((String, String, String, Integer, Integer), util.List[String]))] = kafkaDStream.mapPartitions(part => {
			val redisCli = connToRedis("node02", 6379, 3000, 1)
			part.map(item => {
				(item._1, (item._2, redisCli.hmget(item._1, "userID", "name", "address")))
			})
		})
		result.foreachRDD(_.foreach(println))
		ssc.start()
		ssc.awaitTermination()
	}

	/** json解析 */
	def parseJson(log: String): JSONObject = {
		var ret: JSONObject = null
		try {
			ret = JSON.parseObject(log)
		} catch {
			//异常json数据处理
			case e: JSONException => println(log)
		}
		ret
	}

	/** 连接到redis */
	def connToRedis(redisHost: String, redisPort: Int, timeout: Int, dbNum: Int): Jedis = {
		val redisCli = new Jedis(redisHost, redisPort, timeout)
		redisCli.connect()
		redisCli.select(dbNum)
		redisCli
	}
}