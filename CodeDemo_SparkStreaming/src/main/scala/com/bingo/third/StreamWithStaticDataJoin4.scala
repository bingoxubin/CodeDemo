package com.bingo.third

import com.alibaba.fastjson.{JSON, JSONException, JSONObject}
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf}
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
import org.apache.spark.streaming.{Durations, StreamingContext}

object StreamWithStaticDataJoin4 {
	def main(args: Array[String]): Unit = {

		//设置日志等级
		Logger.getLogger("org").setLevel(Level.WARN)

		//Kafka 参数
		val kafkaParams1 = Map[String, Object](
			"bootstrap.servers" -> "node01:9092,node02:9092,node03:9092",
			"key.deserializer" -> classOf[StringDeserializer],
			"value.deserializer" -> classOf[StringDeserializer],
			"auto.offset.reset" -> "latest",
			"enable.auto.commit" -> (true: java.lang.Boolean),
			"group.id" -> "join_stream_group1")

		val kafkaParams2 = Map[String, Object](
			"bootstrap.servers" -> "node01:9092,node02:9092,node03:9092",
			"key.deserializer" -> classOf[StringDeserializer],
			"value.deserializer" -> classOf[StringDeserializer],
			"auto.offset.reset" -> "latest",
			"enable.auto.commit" -> (true: java.lang.Boolean),
			"group.id" -> "join_stream_group2_new_group")


		//spark环境
		val sparkConf = new SparkConf().setAppName(this.getClass.getSimpleName.replace("$", "")).setMaster("local[10]")
		val ssc = new StreamingContext(sparkConf, Durations.seconds(5))

		/** 1) 流式数据: 用户发的tweet数据 */
		/** 数据示例：
		  * eventTime：事件时间、retweetCount：转推数、language：语言、userID：用户ID、favoriteCount：点赞数、id：事件ID
		  * {"eventTime": "2018-11-05 10:04:00", "retweetCount": 1, "language": "chinese", "userID": "user_1", "favoriteCount": 1, "id": 4909846540155641457} */
		val kafkaDStream1 = KafkaUtils.createDirectStream[String, String](
			ssc,
			LocationStrategies.PreferConsistent,
			ConsumerStrategies.Subscribe[String, String](List("joinstream1"), kafkaParams1)
		).map(item => parseJson(item.value())).map(item => {
			val userID = item.getString("userID")
			val eventTime = item.getString("eventTime")
			val language = item.getString("language")
			val favoriteCount = item.getInteger("favoriteCount")
			val retweetCount = item.getInteger("retweetCount")
			println(userID)
			(userID, (userID, eventTime, language, favoriteCount, retweetCount))
		})

		kafkaDStream1.foreachRDD(eachRdd => {
			eachRdd.foreach(eachLine => {
				println(eachLine._1)
				println(eachLine._2)

			})

		})


		/** 2) 流式数据: 用户发的tweet数据 */
		/** 数据示例：
		  * eventTime：事件时间、retweetCount：转推数、language：语言、userID：用户ID、favoriteCount：点赞数、id：事件ID
		  * {"eventTime": "2018-11-05 10:04:00", "retweetCount": 1, "language": "chinese", "userID": "user_1", "favoriteCount": 1, "id": 4909846540155641457} */


		val kafkaDStream2 = KafkaUtils.createDirectStream[String, String](
			ssc,
			LocationStrategies.PreferConsistent,
			ConsumerStrategies.Subscribe[String, String](List("joinstream2"), kafkaParams2)
		).map(item => parseJson(item.value())).map(item => {
			val userID = item.getString("userID")
			val eventTime = item.getString("eventTime")
			val language = item.getString("language")
			val favoriteCount = item.getInteger("favoriteCount")
			val retweetCount = item.getInteger("retweetCount")
			println(userID)
			(userID, (userID, eventTime, language, favoriteCount, retweetCount))
		})


		kafkaDStream2.foreachRDD(eachRDD => {
			eachRDD.foreach(eachLine => {
				println(eachLine._1)
				println(eachLine._2)

			})

		})
		/** 3) Stream-Stream Join */
		val joinedDStream = kafkaDStream1.leftOuterJoin(kafkaDStream2)
		joinedDStream.foreachRDD(_.foreach(println))
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