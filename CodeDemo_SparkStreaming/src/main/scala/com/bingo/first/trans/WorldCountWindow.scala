package com.bingo.first.trans

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * todo:实现每隔4秒统计6秒的数据
  */
object WorldCountWindow {
	def main(args: Array[String]) {
		val conf = new SparkConf().setMaster("local[2]").setAppName("NetworkWordCount")
		val ssc = new StreamingContext(conf, Seconds(3))
		ssc.checkpoint("./ck")

		// Create a DStream that will connect to hostname:port, like localhost:9999
		val lines = ssc.socketTextStream("linux1", 9999)

		// Split each line into words
		val words = lines.flatMap(_.split(" "))

		// Count each word in each batch
		val pairs = words.map(word => (word, 1))

		val wordCounts = pairs.reduceByKeyAndWindow((a: Int, b: Int) => (a + b), Seconds(12), Seconds(6))

		// Print the first ten elements of each RDD generated in this DStream to the console
		wordCounts.print()

		ssc.start() // Start the computation
		ssc.awaitTermination() // Wait for the computation to terminate
	}
}
