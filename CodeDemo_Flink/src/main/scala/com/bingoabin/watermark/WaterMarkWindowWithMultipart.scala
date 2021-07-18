package com.bingoabin.watermark
//#本地测试的过程中，如果不设置并行度的话，默认读取本机CPU数量设置并行度，可以手动设置并行度environment.setParallelism(1)，每一个线程都会有一个watermark.
//#多并行度的情况下,一个window可能会接受到多个不同线程waterMark，watermark对齐会取所有channel最小的watermark，以最小的watermark为准。

import org.apache.flink.api.java.tuple.Tuple
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.api.scala.function.ProcessWindowFunction
import org.apache.flink.streaming.api.watermark.Watermark
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.api.windowing.windows.TimeWindow
import org.apache.flink.util.Collector

/**
 * 得到并打印每隔 5 秒钟统计前 5秒内的相同的 key 的所有的事件
 * 测试多并行度下的watermark
 */
object WaterMarkWindowWithMultipart {
	def main(args: Array[String]): Unit = {
		//todo:1.构建流式处理环境
		val environment: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
		import org.apache.flink.api.scala._

		//设置并行度为2
		environment.setParallelism(2)
		//todo:2.设置时间类型
		environment.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)

		//todo:3.获取数据源
		val sourceStream: DataStream[String] = environment.socketTextStream("node01", 9999)

		//todo:4. 数据处理
		val mapStream: DataStream[(String, Long)] = sourceStream.map(x => (x.split(",")(0), x.split(",")(1).toLong))

		//todo:5. 添加水位线
		mapStream.assignTimestampsAndWatermarks(
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

					val id: Long = Thread.currentThread.getId
					println("当前的线程id:" + id + " |接受到的事件：" + element + " |事件时间： " + currentElementEventTime + " |当前值的watermark:" + getCurrentWatermark().getTimestamp())

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
//输入数据
//000001,1461756862000
//000001,1461756864000
//000001,1461756866000
//000001,1461756870000
//000001,1461756871000

//结果分析
//当前的线程id:65 |接受到的事件：(000001,1461756862000) |事件时间： 1461756862000 |当前值的watermark:1461756857000
//	当前的线程id:64 |接受到的事件：(000001,1461756864000) |事件时间： 1461756864000 |当前值的watermark:1461756859000
//	当前的线程id:65 |接受到的事件：(000001,1461756866000) |事件时间： 1461756866000 |当前值的watermark:1461756861000
//	当前的线程id:64 |接受到的事件：(000001,1461756870000) |事件时间： 1461756870000 |当前值的watermark:1461756865000
//	当前的线程id:65 |接受到的事件：(000001,1461756871000) |事件时间： 1461756871000 |当前值的watermark:1461756866000
//	窗口的数据条数:2 |窗口的第一条数据：(000001,1461756862000) |窗口的最后一条数据：(000001,1461756864000) |窗口的开始时间： 1461756860000 |窗口的结束时间： 1461756865000 |当前的watermark:1461756865000
//	2> (000001,2)
