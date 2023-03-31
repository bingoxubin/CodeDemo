package com.bingo.operator

import org.apache.spark.{SparkConf, SparkContext}

object SparkCount {
	//def main(args: Array[String]): Unit = {
	//	val sparkConf: SparkConf = new SparkConf().setMaster("local[2]").setAppName("sparkCount")
	//	val sparkContext: SparkContext = SparkContext.getOrCreate(sparkConf)
	//	sparkContext.setLogLevel("WARN")
	//	val wholeWords: RDD[String] = sparkContext.textFile("E:\\60.test\\test.txt")
	//	val collectResult: Array[(String, Int)] = wholeWords.flatMap(x => x.split(" ")).map(x =>(x,1)).reduceByKey((x,y) => x +y).collect()
	//	println(collectResult.toBuffer)
	//}

	//def main(args: Array[String]): Unit = {
	//	val sparkConf: SparkConf = new SparkConf().setAppName("sparkCount")//.setMaster("local[2]")
	//	val sparkContext: SparkContext = SparkContext.getOrCreate(sparkConf)
	//	sparkContext.setLogLevel("WARN")
	//	// val wholeWords: RDD[String] = sparkContext.textFile("hdfs://node01:8020/spark_count")
	//	val wholeWords: RDD[String] = sparkContext.textFile(args(0))
	//	val resultRDD: RDD[(String, Int)] = wholeWords.flatMap(x => x.split(" ")).map(x =>(x,1)).reduceByKey((x,y) => x +y)
	//	// resultRDD.saveAsTextFile("hdfs://node01:8020/spark_count_out_result")
	//	resultRDD.saveAsTextFile(args(1))
	//	sparkContext.stop()
	//}

	def main(args: Array[String]): Unit = {
		val conf = new SparkConf().setAppName("wordcount").setMaster("local")
		val sc = new SparkContext(conf)
		sc.setLogLevel("ERROR")
		//val result: Array[(String, Int)] = sc.textFile("E:\\60.test\\littlefile").flatMap(_.split(" ")).map((_, 1)).reduceByKey((_ + _)).collect
		val result: Array[(String, Int)] = sc.textFile("E:\\60.test\\littlefile").flatMap(_.split(" ")).map((_, 1)).reduceByKey(_ + _, 1).sortBy(_._2, false).collect
		println(result.toBuffer)
		sc.stop()
	}
}
