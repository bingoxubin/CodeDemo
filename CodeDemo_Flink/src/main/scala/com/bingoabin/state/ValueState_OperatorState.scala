package com.bingoabin.state
//1.operator state是task级别的state，说白了就是每个task对应一个state
//2.Kafka Connector source中的每个分区（task）都需要记录消费的topic的partition和offset等信息。
//3.operator state 只有一种托管状态：ValueState

//需求：Operator State案例演示
//实现每两条数据进行输出打印一次，不用区分数据的key
//这里使用ListState实现

import org.apache.flink.streaming.api.functions.sink.SinkFunction
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import scala.collection.mutable.ListBuffer

//实现每两条数据进行输出打印一次，不用区分数据的key
object ValueState_OperatorState {
	def main(args: Array[String]): Unit = {
		val env = StreamExecutionEnvironment.getExecutionEnvironment
		import org.apache.flink.api.scala._
		val sourceStream: DataStream[(String, Int)] = env.fromCollection(List(
			("spark", 3),
			("hadoop", 5),
			("hive", 7),
			("flume", 9)
		))
		sourceStream.addSink(new OperateTaskState).setParallelism(1)
		env.execute()
	}
}

class OperateTaskState extends SinkFunction[(String, Int)] {
	//定义一个list 用于我们每两条数据打印一下
	private var listBuffer: ListBuffer[(String, Int)] = new ListBuffer[(String, Int)]

	override def invoke(value: (String, Int), context: SinkFunction.Context[_]): Unit = {
		listBuffer.+=(value)

		if (listBuffer.size == 2) {
			println(listBuffer)

			//清空state状态
			listBuffer.clear()
		}
	}
}
