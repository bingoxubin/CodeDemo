package com.bingoabin.table
//#Flink 支持 ProcessTime、EventTime 和 IngestionTime 三种时间概念，针对每种时间概念，Flink Table API 中使用 Schema 中单独的字段来表示时间属性，当时间字段被指定后，就可以在基于时间的操作算子中使用相应的时间属性。
//#在 Table API 中通过使用.rowtime 来定义 EventTime 字段，在 ProcessTime 时间字段名后使用.proctime 后缀来指定 ProcessTime 时间属性
//#需求:统计最近 5 秒钟，每个单词出现的次数

import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.api.watermark.Watermark
import org.apache.flink.table.api.{GroupWindowedTable, Table, Tumble}
import org.apache.flink.table.api.scala.StreamTableEnvironment
import org.apache.flink.types.Row

//基于table的window窗口操作处理延迟数据
object WindowWatermarkTable {

	//定义样例类
	case class Message(word: String, createTime: Long)

	def main(args: Array[String]): Unit = {
		//todo:1、构建流处理环境
		val streamEnv: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
		streamEnv.setParallelism(1)

		import org.apache.flink.api.scala._

		//指定EventTime为时间语义
		streamEnv.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)

		//todo: 2、构建StreamTableEnvironment
		val tableEnvironment: StreamTableEnvironment = StreamTableEnvironment.create(streamEnv)

		//todo： 3、接受socket数据
		val sourceStream: DataStream[String] = streamEnv.socketTextStream("node01", 9999)

		//todo: 4、数据切分处理
		val mapStream: DataStream[Message] = sourceStream.map(x => Message(x.split(",")(0), x.split(",")(1).toLong))

		//todo: 5、添加watermark
		val watermarksStream: DataStream[Message] = mapStream.assignTimestampsAndWatermarks(new AssignerWithPeriodicWatermarks[Message] {

			//定义延迟时长
			val maxOutOfOrderness = 5000L
			//历史最大事件时间
			var currentMaxTimestamp: Long = _

			override def getCurrentWatermark: Watermark = {
				val watermark = new Watermark(currentMaxTimestamp - maxOutOfOrderness)
				watermark
			}

			override def extractTimestamp(element: Message, previousElementTimestamp: Long): Long = {

				val eventTime: Long = element.createTime
				currentMaxTimestamp = Math.max(eventTime, currentMaxTimestamp)
				eventTime
			}
		})


		//todo:6、构建Table , 设置时间属性
		import org.apache.flink.table.api.scala._
		val table: Table = tableEnvironment.fromDataStream(watermarksStream, 'word, 'createTime.rowtime)
		//todo:7、添加window
		//滚动窗口第一种写法
		//val windowedTable: GroupWindowedTable = table.window(Tumble.over("5.second").on("createTime").as("window"))

		//滚动窗口的第二种写法
		val windowedTable: GroupWindowedTable = table.window(Tumble over 5.second on 'createTime as 'window)

		//todo:8、对窗口数据进行处理
		// 使用2个字段分组，窗口名称和单词
		val result: Table = windowedTable.groupBy('window, 'word)
		  //单词、窗口的开始、结束e、聚合计算
		  .select('word, 'window.start, 'window.end, 'word.count)
		//todo:9、将table转换成DataStream
		val resultStream: DataStream[(Boolean, Row)] = tableEnvironment.toRetractStream[Row](result)

		resultStream.filter(x => x._1 == true).print()

		tableEnvironment.execute("table")
	}
}

// 发送数据
//hadoop,1461756862000
//hadoop,1461756866000
//hadoop,1461756864000
//hadoop,1461756870000
//hadoop,1461756875000
