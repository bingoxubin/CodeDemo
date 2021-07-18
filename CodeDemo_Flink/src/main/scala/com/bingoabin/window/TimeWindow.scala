package com.bingoabin.window
//#Time Window窗口的应用
//#time window又分为滚动窗口和滑动窗口，这两种窗口调用方法都是一样的，都是调用timeWindow这个方法，如果传入一个参数就是滚动窗口，如果传入两个参数就是滑动窗口
//需求：每隔5s时间，统计最近10s出现的数据
//代码实现：

import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.api.windowing.time.Time

object TimeWindow {
	def main(args: Array[String]): Unit = {
		val environment: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
		import org.apache.flink.api.scala._
		val socketSource: DataStream[String] = environment.socketTextStream("node01", 9999)

		socketSource
		  .flatMap(x => x.split(" "))
		  .map(x => (x, 1))
		  .keyBy(0)
		  .timeWindow(Time.seconds(10), Time.seconds(5))
		  .sum(1).print()
		environment.execute()
	}
}
