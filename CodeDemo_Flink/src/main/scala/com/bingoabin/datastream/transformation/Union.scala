package com.bingoabin.datastream.transformation

import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}

//把2个流的数据进行合并，2个流的数据类型必须保持一致
object Union {
	def main(args: Array[String]): Unit = {
		val environment: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
		import org.apache.flink.api.scala._
		val firstStream: DataStream[String] = environment.fromCollection(Array("hello spark", "hello flink"))
		val secondStream: DataStream[String] = environment.fromCollection(Array("hadoop spark", "hive flink"))
		//两个流合并成为一个流，必须保证两个流当中的数据类型是一致的
		val resultStream: DataStream[String] = firstStream.union(secondStream)
		resultStream.print()
		environment.execute()
	}
}
