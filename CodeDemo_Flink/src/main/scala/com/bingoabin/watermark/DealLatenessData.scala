package com.bingoabin.watermark
//#Window 的allowedLateness处理延迟太大的数据
//#基于 Event-Time 的窗口处理流式数据，虽然提供了 Watermark 机制，却只能在一定程度上解决了数据乱序的问题。但在某些情况下数据可能延时会非常严重，即使通过 Watermark 机制也无法等到数据全部进入窗口再进行处理。
//
//#Flink 中默认会将这些迟到的数据做丢弃处理，但是有些时候用户希望即使数据延迟到达的情况下，也能够正常按照流程处理并输出结果，此时就需要使用 Allowed Lateness 机制来对迟到的数据进行额外的处理。
//
//#迟到数据的处理机制
//#1、直接丢弃
//#2、指定允许再次迟到的时间
//    //例如
//    assignTimestampsAndWatermarks(new EventTimeExtractor() )
//    .keyBy(0)
//    .timeWindow(Time.seconds(3))
//    .allowedLateness(Time.seconds(2)) // 允许事件再迟到2秒
//    .process(new SumProcessWindowFunction())
//    .print().setParallelism(1);
//
//    //注意：
//    //（1）. 当我们设置允许迟到2秒的事件，第一次 window 触发的条件是 watermark >= window_end_time
//    //（2）. 第二次(或者多次)触发的条件是watermark < window_end_time + allowedLateness
//#3、收集迟到太多的数据
////例如
//assignTimestampsAndWatermarks(new EventTimeExtractor() )
//.keyBy(0)
//.timeWindow(Time.seconds(3))
//.allowedLateness(Time.seconds(2)) //允许事件再迟到2秒
//.sideOutputLateData(outputTag)    //收集迟到太多的数据
//.process(new SumProcessWindowFunction())
//.print().setParallelism(1);

import org.apache.flink.api.java.tuple.Tuple
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks
import org.apache.flink.streaming.api.scala.function.ProcessWindowFunction
import org.apache.flink.streaming.api.scala.{DataStream, OutputTag, StreamExecutionEnvironment}
import org.apache.flink.streaming.api.watermark.Watermark
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.api.windowing.windows.TimeWindow
import org.apache.flink.util.Collector

//运行数据再次延延迟一段时间，并且对延迟太多的数据进行收集
object DealLatenessData {
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
		//定义一个侧输出流的标签，用于收集迟到太多的数据
		val lateTag = new OutputTag[(String, Long)]("late")
		//todo:5.  数据计算--添加水位线
		val result: DataStream[(String, Long)] = mapStream.assignTimestampsAndWatermarks(
			new AssignerWithPeriodicWatermarks[(String, Long)] {

				//定义延迟时间长度
				//表示在5秒以内的数据延时有效，超过5秒的数据被认定为迟到事件
				val maxOutOfOrderness = 5000L
				//历史最大事件时间
				var currentMaxTimestamp: Long = _

				//周期性的生成水位线watermark
				override def getCurrentWatermark: Watermark = {
					val watermark = new Watermark(currentMaxTimestamp - maxOutOfOrderness)
					watermark
				}

				//抽取事件时间
				override def extractTimestamp(element: (String, Long), previousElementTimestamp: Long): Long = {
					//获取事件时间
					val currentElementEventTime: Long = element._2
					//对比当前事件时间和历史最大事件时间, 将较大值重新赋值给currentMaxTimestamp
					currentMaxTimestamp = Math.max(currentMaxTimestamp, currentElementEventTime)
					println("接受到的事件：" + element + " |事件时间： " + currentElementEventTime)
					currentElementEventTime
				}
			})
		  .keyBy(0)
		  .timeWindow(Time.seconds(5))
		  .allowedLateness(Time.seconds(2)) //运行数据再次延迟2s
		  .sideOutputLateData(lateTag) //收集延迟大多的数据
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
		  })
		//打印延迟太多的数据
		result.getSideOutput(lateTag).print("late")
		//打印
		result.print("ok")
		environment.execute()
	}
}
//发送数据
//000001, 1461756862000
//000001, 1461756866000
//000001, 1461756868000
//000001, 1461756869000
//000001, 1461756870000
//000001, 1461756862000
//000001, 1461756871000
//000001, 1461756872000
//000001, 1461756862000
//000001, 1461756863000