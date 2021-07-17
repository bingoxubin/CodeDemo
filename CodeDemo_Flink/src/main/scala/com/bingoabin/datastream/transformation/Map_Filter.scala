package com.bingoabin.datastream.transformation

import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}

//map filter
object Map_Filter {
	def main(args: Array[String]): Unit = {
		val environment: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
		import org.apache.flink.api.scala._
		val sourceStream: DataStream[Int] = environment.fromElements(1, 2, 3, 4, 5, 6)
		val mapStream: DataStream[Int] = sourceStream.map(x => x * 10)
		val resultStream: DataStream[Int] = mapStream.filter(x => x % 2 == 0)
		resultStream.print()
		environment.execute()
	}
}
