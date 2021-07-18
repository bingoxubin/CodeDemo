package com.bingoabin.watermark
//#有序数据流中引入 Watermark 和 EventTime
//#对于有序的数据，代码比较简洁，主要需要从源 Event 中抽取 EventTime
//#需求:对socket中有序（按照时间递增）的数据流，进行每5s处理一次

import org.apache.flink.api.java.tuple.Tuple
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.scala.function.ProcessWindowFunction
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.api.windowing.windows.TimeWindow
import org.apache.flink.util.Collector

object OrderedStreamWaterMark {
	def main(args: Array[String]): Unit = {
		//todo:1.构建流式处理环境
		val environment: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
		import org.apache.flink.api.scala._
		environment.setParallelism(1)
		//todo:2.设置时间类型
		environment.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
		//todo:3.获取数据源
		val sourceStream: DataStream[String] = environment.socketTextStream("node01", 9999)
		//todo:4. 数据处理
		val mapStream: DataStream[(String, Long)] = sourceStream.map(x => (x.split(",")(0), x.split(",")(1).toLong))
		//todo: 5.从源Event中抽取eventTime
		val watermarkStream: DataStream[(String, Long)] = mapStream.assignAscendingTimestamps(x => x._2)
		//todo:6. 数据计算
		watermarkStream.keyBy(0)
		  .timeWindow(Time.seconds(5))
		  .process(new ProcessWindowFunction[(String, Long), (String, Long), Tuple, TimeWindow] {
			  override def process(key: Tuple, context: Context, elements: Iterable[(String, Long)], out: Collector[(String, Long)]): Unit = {
				  val value: String = key.getField[String](0)
				  //窗口的开始时间
				  val startTime: Long = context.window.getStart
				  //窗口的结束时间
				  val startEnd: Long = context.window.getEnd
				  //获取当前的 watermark
				  val watermark: Long = context.currentWatermark
				  var sum: Long = 0
				  val toList: List[(String, Long)] = elements.toList
				  for (eachElement <- toList) {
					  sum += 1
				  }
				  println("窗口的数据条数:" + sum +
					" |窗口的第一条数据：" + toList.head +
					" |窗口的最后一条数据：" + toList.last +
					" |窗口的开始时间： " + startTime +
					" |窗口的结束时间： " + startEnd +
					" |当前的watermark:" + watermark)
				  out.collect((value, sum))
			  }
		  }).print()
		environment.execute()
	}
}

//发送数据
//000001,1461756862000
//000001,1461756866000
//000001,1461756872000
//000001,1461756873000
//000001,1461756874000
//000001,1461756875000
