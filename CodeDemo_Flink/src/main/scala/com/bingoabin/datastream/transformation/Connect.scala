package com.bingoabin.datastream.transformation

import org.apache.flink.streaming.api.functions.co.CoFlatMapFunction
import org.apache.flink.streaming.api.scala.{ConnectedStreams, DataStream, StreamExecutionEnvironment}
import org.apache.flink.util.Collector

//和union类似，但是只能连接两个流，两个流的数据类型可以不同，会对两个流中的数据应用不同的处理方法
object Connect {
	def main(args: Array[String]): Unit = {
		val environment: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
		environment.setParallelism(1)
		import org.apache.flink.api.scala._
		val firstStream: DataStream[String] = environment.fromCollection(Array("hello world", "spark flink"))
		val secondStream: DataStream[Int] = environment.fromCollection(Array(1, 2, 3, 4))
		//调用connect方法连接多个DataStream
		val connectStream: ConnectedStreams[String, Int] = firstStream.connect(secondStream)
		val unionStream: DataStream[Any] = connectStream.map(x => x + "abc", y => y * 2)
		val coFlatMapStream: DataStream[String] = connectStream.flatMap(new CoFlatMapFunction[String, Int, String] {
			//对第一个流中的数据操作
			override def flatMap1(value: String, out: Collector[String]): Unit = {
				out.collect(value.toUpperCase())
			}

			//对第二个流中的数据操作
			override def flatMap2(value: Int, out: Collector[String]): Unit = {
				out.collect(value * 2 + "")
			}
		})
		coFlatMapStream.print()
		unionStream.print()
		environment.execute()
	}
}
