package com.bingoabin.counter

import org.apache.flink.api.common.accumulators.LongCounter
import org.apache.flink.api.common.functions.RichMapFunction
import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.configuration.Configuration

//需求:通过计数器来实现统计文件当中Exception关键字出现的次数
object Counter {
	def main(args: Array[String]): Unit = {
		val env = ExecutionEnvironment.getExecutionEnvironment
		import org.apache.flink.api.scala._
		//统计tomcat日志当中exception关键字出现了多少次
		val sourceDataSet: DataSet[String] = env.readTextFile("E:\\catalina.out")
		sourceDataSet.map(new RichMapFunction[String, String] {
			//创建累加器
			var counter = new LongCounter()

			override def open(parameters: Configuration): Unit = {
				//注册累加器
				getRuntimeContext.addAccumulator("my-accumulator", counter)
			}

			//实现业务逻辑
			override def map(value: String): String = {
				if (value.toLowerCase().contains("exception")) {
					//满足条件累加器加1
					counter.add(1)
				}
				value
			}
		}).writeAsText("E:\\test")
		val job = env.execute()
		//获取累加器，并打印累加器的值
		val count = job.getAccumulatorResult[Long]("my-accumulator")
		//打印
		println(count)
	}
}
