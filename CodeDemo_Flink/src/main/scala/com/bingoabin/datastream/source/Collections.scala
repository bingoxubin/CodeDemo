package com.bingoabin.datastream.source

import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}

//fromCollection(Collection)
//通过collection集合创建一个数据流，集合中的所有元素必须是相同类型的。
object Collections {
	def main(args: Array[String]): Unit = {
		val environment: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
		//导入隐式转换的包
		import org.apache.flink.api.scala._
		//准备数据源--数组
		val array = Array("hello world", "world spark", "flink test", "spark hive", "test")
		val fromArray: DataStream[String] = environment.fromCollection(array)
		//  val value: DataStream[String] = environment.fromElements("hello world")
		val resultDataStream: DataStream[(String, Int)] = fromArray
		  .flatMap(x => x.split(" "))
		  .map(x => (x, 1))
		  .keyBy(0)
		  .sum(1)
		//打印
		resultDataStream.print()
		//启动
		environment.execute()
	}
}
