package com.bingoabin.datastream.source

import org.apache.flink.streaming.api.functions.source.{ParallelSourceFunction, SourceFunction}
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}

//2、自定义多并行度数据源
//继承ParallelSourceFunction来自定义多并行度的source

//多并行度的source
object CustomSource_Parallel {
	def main(args: Array[String]): Unit = {
		//构建流处理环境
		val environment: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
		import org.apache.flink.api.scala._
		//添加source
		val getSource: DataStream[Long] = environment.addSource(new MultipartSource).setParallelism(2)
		//处理
		val resultStream: DataStream[Long] = getSource.filter(x => x % 2 == 0)
		resultStream.setParallelism(2).print()
		environment.execute()
	}
}

//继承ParallelSourceFunction来自定义多并行度的source
class MultipartSource extends ParallelSourceFunction[Long] {
	private var number = 1L
	private var isRunning = true

	override def run(sourceContext: SourceFunction.SourceContext[Long]): Unit = {
		while (true) {
			number += 1
			sourceContext.collect(number)
			Thread.sleep(1000)
		}
	}

	override def cancel(): Unit = {
		isRunning = false
	}
}
