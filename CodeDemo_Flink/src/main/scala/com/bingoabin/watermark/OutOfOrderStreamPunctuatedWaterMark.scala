package com.bingoabin.watermark

//#乱序数据流中引入 Watermark 和 EventTime
//#对于乱序数据流，有两种常见的引入方法：周期性和间断性
//
//#2、With Punctuated（间断性的） Watermark
//间断性的生成 Watermark 一般是基于某些事件触发 Watermark 的生成和发送。
//比如说只给用户id为000001的添加watermark，其他的用户就不添加
//
//#需求:对socket中无序数据流，进行每5s处理一次，数据中会有延迟

import org.apache.flink.api.java.tuple.Tuple
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.functions.AssignerWithPunctuatedWatermarks
import org.apache.flink.streaming.api.scala.function.ProcessWindowFunction
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.api.watermark.Watermark
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.api.windowing.windows.TimeWindow
import org.apache.flink.util.Collector

//对无序的数据流间断性的添加水印
object OutOfOrderStreamPunctuatedWaterMark {
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
		//todo:5. 添加水位线
		mapStream.assignTimestampsAndWatermarks(
			new AssignerWithPunctuatedWatermarks[(String, Long)] {
				//定义延迟时间长度
				//表示在5秒以内的数据延时有效，超过5秒的数据被认定为迟到事件
				val maxOutOfOrderness = 5000L
				//历史最大事件时间
				var currentMaxTimestamp: Long = _

				override def checkAndGetNextWatermark(lastElement: (String, Long), extractedTimestamp: Long): Watermark = {
					//当用户id为000001生成watermark
					if (lastElement._1.equals("000001")) {
						val watermark = new Watermark(currentMaxTimestamp - maxOutOfOrderness)
						watermark
					} else {
						//其他情况下不返回水位线
						null
					}
				}

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
//000001, 1461756862000
//000001, 1461756866000
//000001, 1461756872000
//000002, 1461756866000
//000002, 1461756868000
