package com.bingoabin.time

//ProcessWindowFunction实现时间确定
//需求:通过process实现处理时间的确定，包括数据时间、window时间等

import org.apache.commons.lang3.time.FastDateFormat
import org.apache.flink.api.java.tuple.Tuple
import org.apache.flink.streaming.api.scala.function.ProcessWindowFunction
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.api.windowing.windows.TimeWindow
import org.apache.flink.util.Collector

//通过process实现处理时间的确定，包括数据时间，window时间等
object ProcessTime {
	def main(args: Array[String]): Unit = {
		val environment: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
		import org.apache.flink.api.scala._
		val socketSource: DataStream[String] = environment.socketTextStream("node01", 9999)
		//对数据进行处理
		socketSource.flatMap(x => x.split(" "))
		  .map(x => (x, 1))
		  .keyBy(0)
		  .timeWindow(Time.seconds(2), Time.seconds(1))
		  .process(new SumProcessFunction)
		  .print()
		environment.execute()
	}
}

class SumProcessFunction extends ProcessWindowFunction[(String, Int), (String, Int), Tuple, TimeWindow] {
	val format: FastDateFormat = FastDateFormat.getInstance("HH:mm:ss")

	override def process(key: Tuple, context: Context, elements: Iterable[(String, Int)], out: Collector[(String, Int)]): Unit = {
		println("当前系统时间为：" + format.format(System.currentTimeMillis()))
		println("window的处理时间为：" + format.format(context.currentProcessingTime))
		println("window的开始时间为：" + format.format(context.window.getStart))
		println("window的结束时间为：" + format.format(context.window.getEnd))
		var sum: Int = 0
		for (eachElement <- elements) {
			sum += eachElement._2
		}
		out.collect((key.getField(0), sum))
	}
}