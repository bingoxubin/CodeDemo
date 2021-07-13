package com.bingoabin.datastream.source

import org.apache.flink.streaming.api.functions.source.SourceFunction
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
//addSource
//可以实现读取第三方数据源的数据

//1、自定义单并行度数据源
//继承SourceFunction来自定义单并行度source

//自定义单并行度source
object CustomSource_Serial {
	def main(args: Array[String]): Unit = {
		val environment: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
		import org.apache.flink.api.scala._
		val getSource: DataStream[Long] = environment.addSource(new MySource).setParallelism(1)
		val resultStream: DataStream[Long] = getSource.filter(x => x % 2 == 0)
		resultStream.setParallelism(1).print()
		environment.execute()
	}
}

//继承SourceFunction来自定义单并行度source
class MySource extends SourceFunction[Long] {
	private var number = 1L
	private var isRunning = true

	override def run(sourceContext: SourceFunction.SourceContext[Long]): Unit = {
		while (isRunning) {
			number += 1
			sourceContext.collect(number)
			Thread.sleep(1000)
		}
	}

	override def cancel(): Unit = {
		isRunning = false
	}
}
