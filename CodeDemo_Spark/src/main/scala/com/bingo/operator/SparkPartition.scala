package com.bingo.operator

import org.apache.spark.rdd.RDD
import org.apache.spark.{Partitioner, SparkConf, SparkContext}

object SparkPartition {
	def main(args: Array[String]): Unit = {
		val context: SparkContext = SparkContext.getOrCreate(new SparkConf().setMaster("local[4]").setAppName("sparkPartitioner"))
		context.setLogLevel("WARN")
		val partition = new MyPartition(8)
		val getTextFile: RDD[String] = context.textFile("file:///D:\\开课吧课程资料\\15、scala与spark课程资料\\2、spark课程\\spark_day03\\2、数据准备\\上网数据", 4)
		val splitTuple: RDD[(String, String)] = getTextFile.map(x => {
			val strings: Array[String] = x.split("@zolen@")
			// val host = new URL(strings(16)).getHost
			// (host, (url, t._2))
			if (strings.length >= 16) {
				(strings(16), x)
			} else {
				("http://www.baidu.com", x)
			}
		})
		//执行分区策略，将相同的hosts划分到同一个分区里面去
		splitTuple.partitionBy(partition).saveAsTextFile("file:///d:\\out_partition.txt")
		context.stop()
	}
}

class MyPartition(partitionsNum: Int) extends Partitioner {
	override def numPartitions: Int = {
		partitionsNum
	}

	override def getPartition(key: Any): Int = {
		if (key.toString.startsWith("http")) {
			val domain = new java.net.URL(key.toString).getHost()
			val returnResult: Int = (domain.hashCode & Integer.MAX_VALUE) % partitionsNum
			returnResult
		} else {
			0
		}

	}
}
