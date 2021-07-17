package com.bingoabin.state
//#作用：保存一个可以更新和检索的值
//#需求：使用valueState实现平均值求取

import org.apache.flink.api.common.functions.RichFlatMapFunction
import org.apache.flink.api.common.state.{ValueState, ValueStateDescriptor}
import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.util.Collector

/**
 * 使用valueState实现平均值求取
 */
object KeyedState_ValueState {
	def main(args: Array[String]): Unit = {
		val env = StreamExecutionEnvironment.getExecutionEnvironment
		import org.apache.flink.api.scala._

		env.fromCollection(List(
			(1L, 3d),
			(1L, 5d),
			(1L, 7d),
			(1L, 4d),
			(1L, 2d)
		))
		  .keyBy(_._1)
		  .flatMap(new CountAverageWithValue())
		  .print()
		env.execute()
	}
}

class CountAverageWithValue extends RichFlatMapFunction[(Long, Double), (Long, Double)] {
	//定义ValueState类型的变量
	private var sum: ValueState[(Long, Double)] = _

	override def open(parameters: Configuration): Unit = {
		//初始化获取历史状态的值
		sum = getRuntimeContext.getState(
			new ValueStateDescriptor[(Long, Double)]("average", classOf[(Long, Double)])
		)
	}

	override def flatMap(input: (Long, Double), out: Collector[(Long, Double)]): Unit = {
		// access the state value
		val tmpCurrentSum = sum.value
		// If it hasn't been used before, it will be null
		val currentSum = if (tmpCurrentSum != null) {
			tmpCurrentSum
		} else {
			(0L, 0d)
		}
		// update the count
		val newSum = (currentSum._1 + 1, currentSum._2 + input._2)

		// update the state
		sum.update(newSum)

		// if the count reaches 2, emit the average and clear the state
		if (newSum._1 >= 2) {
			out.collect((input._1, newSum._2 / newSum._1))
			//将状态清除
			//sum.clear()
		}
	}
}
