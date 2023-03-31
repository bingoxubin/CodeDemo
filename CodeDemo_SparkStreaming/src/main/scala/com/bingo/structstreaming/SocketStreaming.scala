package com.bingo.structstreaming

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object SocketStreaming {
	def main(args: Array[String]): Unit = {
		Logger.getLogger("org").setLevel(Level.ERROR)

		val sparkConf: SparkConf = new SparkConf().setAppName("socketStreaming").setMaster("local[4]")
		val spark: SparkSession = SparkSession.builder().config(sparkConf).getOrCreate()
		val lines = spark.readStream
		  .format("socket")
		  .option("host", "node01")
		  .option("port", 9999)
		  .load()
		import spark.implicits._
		// Split the lines into words
		val words = lines.as[String].flatMap(_.split(" "))
		// Generate running word count
		val wordCounts = words.groupBy("value").count()
		// Start running the query that prints the running counts to the console
		val query = wordCounts.writeStream
		  .outputMode("complete")
		  .format("console")
		  .start()
		query.awaitTermination()
	}
}
