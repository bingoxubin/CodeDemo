package com.bingo.third

import com.alibaba.fastjson.{JSON, JSONException, JSONObject}
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
import org.apache.spark.streaming.{Durations, StreamingContext}


case class UserInfo2(userID: String, userName: String, userAddress: String)

object StreamWithStaticDataJoin2 {
	def main(args: Array[String]): Unit = {
		//设置日志等级
		Logger.getLogger("org").setLevel(Level.WARN)
		//Kafka 参数
		val kafkaParams = Map[String, Object](
			"bootstrap.servers" -> "node01:9092,node02:9092,node03:9092",
			"key.deserializer" -> classOf[StringDeserializer],
			"value.deserializer" -> classOf[StringDeserializer],
			"auto.offset.reset" -> "latest",
			"enable.auto.commit" -> (false: java.lang.Boolean),
			"group.id" -> "testGroup2")

		//spark环境
		val sparkConf = new SparkConf().setAppName(this.getClass.getSimpleName.replace("$", "")).setMaster("local[3]")
		val ssc = new StreamingContext(sparkConf, Durations.seconds(5))

		/** 1) 静态数据: 用户基础信息。 将用户基础信息广播出去。 */
		val broadcastUserInfo = ssc.sparkContext.broadcast(
			Map(
				"user_1" -> UserInfo2("user_1", "name_1", "address_1"),
				"user_2" -> UserInfo2("user_2", "name_2", "address_2"),
				"user_3" -> UserInfo2("user_3", "name_3", "address_3"),
				"user_4" -> UserInfo2("user_4", "name_4", "address_4"),
				"user_5" -> UserInfo2("user_5", "name_5", "address_5")
			))


		/** 2) 流式数据: 用户发的tweet数据 */
		/** 数据示例：
		  * eventTime：事件时间、retweetCount：转推数、language：语言、userID：用户ID、favoriteCount：点赞数、id：事件ID
		  * {"eventTime": "2018-11-05 10:04:00", "retweetCount": 1, "language": "chinese", "userID": "user_1", "favoriteCount": 1, "id": 4909846540155641457} */
		val kafkaDStream = KafkaUtils.createDirectStream[String, String](
			ssc,
			LocationStrategies.PreferConsistent,
			ConsumerStrategies.Subscribe[String, String](List("test"), kafkaParams)
		).map(item => parseJson(item.value())).map(item => {
			val userID = item.getString("userID")
			val eventTime = item.getString("eventTime")
			val language = item.getString("language")
			val favoriteCount = item.getInteger("favoriteCount")
			val retweetCount = item.getInteger("retweetCount")
			(userID, (userID, eventTime, language, favoriteCount, retweetCount))
		})
		/** 3) 流与静态数据做Join (Broadcast Join 方式) */
		val result = kafkaDStream.mapPartitions(part => {
			val userInfo = broadcastUserInfo.value
			part.map(item => {
				(item._1, (item._2, userInfo.getOrElse(item._1, null)))
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
}
