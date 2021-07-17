package com.bingoabin.datastream.transformation

import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.api.common.functions.Partitioner

//定义分区class类
object CustomizePartition {
	def main(args: Array[String]): Unit = {
		val environment: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
		import org.apache.flink.api.scala._
		//获取dataStream
		val sourceStream: DataStream[String] = environment.fromElements("hello laowang", "spark flink", "hello tony", "hive hadoop")

		val rePartition: DataStream[String] = sourceStream.partitionCustom(new MyPartitioner, x => x + "")
		rePartition.map(x => {
			println("数据的key为" + x + "线程为" + Thread.currentThread().getId)
			x
		})
		rePartition.print()
		environment.execute()
	}
}

//定义分区类
class MyPartitioner extends Partitioner[String] {
	override def partition(line: String, num: Int): Int = {
		println("分区个数为" + num)
		if (line.contains("hello")) {
			0
		} else {
			1
		}
	}
}
