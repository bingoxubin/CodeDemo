package com.bingo.third

import com.alibaba.fastjson.{JSON, JSONException, JSONObject}
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.kafka010.{CanCommitOffsets, ConsumerStrategies, HasOffsetRanges, KafkaUtils, LocationStrategies, OffsetRange}
import org.apache.spark.streaming.{Durations, StreamingContext}

case class UserInfo(userID: String, userName: String, userAddress: String)

object StreamStaicJoin {

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
			"group.id" -> "testGroup")
		//spark环境
		val sparkConf = new SparkConf().setAppName(this.getClass.getSimpleName.replace("$", "")).setMaster("local[3]")
		val ssc = new StreamingContext(sparkConf, Durations.seconds(5))

		/** 1) 静态数据: 用户基础信息 */
		val userInfo = ssc.sparkContext.parallelize(Array(
			UserInfo("user_1", "name_1", "address_1"),
			UserInfo("user_2", "name_2", "address_2"),
			UserInfo("user_3", "name_3", "address_3"),
			UserInfo("user_4", "name_4", "address_4"),
			UserInfo("user_5", "name_5", "address_5")
		)).map(item => (item.userID, item))

		//对RDD数据进行缓存
		userInfo.persist(StorageLevel.MEMORY_AND_DISK_SER_2)

		/** 2) 流式数据: 用户发的tweet数据 */
		/**
		  * 获取kafka当中的流失数据，关于用户的json格式的数据
		  **/
		val kafkaDStream: InputDStream[ConsumerRecord[String, String]] = KafkaUtils.createDirectStream[String, String](
			ssc,
			LocationStrategies.PreferConsistent,
			ConsumerStrategies.Subscribe[String, String](Set("test"), kafkaParams)
		)

		/** 3) 流与静态数据做Join (RDD Join 方式) */
		kafkaDStream.foreachRDD(eachRDD => {
			val userRDD: RDD[(String, (String, String, String, Integer, Integer))] = eachRDD.map(item => parseJson(item.value())).map(item => {
				val userID = item.getString("userID")
				val eventTime = item.getString("eventTime")
				val language = item.getString("language")
				val favoriteCount = item.getInteger("favoriteCount")
				val retweetCount = item.getInteger("retweetCount")
				(userID, (userID, eventTime, language, favoriteCount, retweetCount))
			})
			val resultRDD: RDD[(String, ((String, String, String, Integer, Integer), UserInfo))] = userRDD.join(userInfo)
			resultRDD.foreach(println)

			val offsetRanges: Array[OffsetRange] = eachRDD.asInstanceOf[HasOffsetRanges].offsetRanges
			kafkaDStream.asInstanceOf[CanCommitOffsets].commitAsync(offsetRanges)
		})
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