package com.bingoabin.datastream.transformation

import org.apache.flink.api.java.tuple.Tuple
import org.apache.flink.streaming.api.scala.{DataStream, KeyedStream, StreamExecutionEnvironment}

//是将输入的 KeyedStream 流通过 传 入 的 用 户 自 定 义 的 ReduceFunction 滚 动 地 进 行 数 据 聚 合 处 理
object Reduce {
	def main(args: Array[String]): Unit = {
		val environment: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
		import org.apache.flink.api.scala._
		val sourceStream: DataStream[(String, Int)] = environment.fromElements(("a", 1), ("a", 2), ("b", 2), ("a", 3), ("c", 2))
		val keyByStream: KeyedStream[(String, Int), Tuple] = sourceStream.keyBy(0)
		val resultStream: DataStream[(String, Int)] = keyByStream.reduce((t1, t2) => (t1._1, t1._2 + t2._2))
		resultStream.print()
		environment.execute()
	}
}
