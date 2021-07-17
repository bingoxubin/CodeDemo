package com.bingoabin.datastream.source

import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.api.windowing.time.Time

//使用滑动窗口
//每隔1秒钟统计最近2秒钟的每个单词出现的次数
object SocketWindow {
	def main(args: Array[String]): Unit = {
		//构建流处理的环境
		val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
		//从socket获取数据
		val sourceStream: DataStream[String] = env.socketTextStream("localhost", 9999)
		//导入隐式转换的包
		import org.apache.flink.api.scala._
		//对数据进行处理
		val result: DataStream[(String, Int)] = sourceStream
		  .flatMap(x => x.split(" ")) //按照空格切分
		  .map(x => (x, 1)) //每个单词计为1
		  .keyBy(0) //按照下标为0的单词进行分组
		  .timeWindow(Time.seconds(2), Time.seconds(1)) //每隔1s处理2s的数据
		  .sum(1) //按照下标为1累加相同单词出现的次数
		//对数据进行打印
		result.print()
		//开启任务
		env.execute("FlinkStream")
	}
}
