package com.bingoabin.state
//#作用：用于数据的聚合
//#需求：使用ReducingState求取每个key对应的平均值

import org.apache.flink.api.common.functions.{ReduceFunction, RichFlatMapFunction}
import org.apache.flink.api.common.state.{ReducingState, ReducingStateDescriptor}
import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.util.Collector

/**
 * ReducingState<T> ：这个状态为每一个 key 保存一个聚合之后的值
 * get() 获取状态值
 * add()  更新状态值，将数据放到状态中
 * clear() 清除状态
 */
object KeyedState_ReducingState {
	def main(args: Array[String]): Unit = {
		val env = StreamExecutionEnvironment.getExecutionEnvironment
		import org.apache.flink.api.scala._
		env.fromCollection(List(
			(1L, 3d),
			(1L, 5d),
			(1L, 7d),
			(2L, 4d),
			(2L, 2d),
			(2L, 6d)
		)).keyBy(_._1)
		  .flatMap(new CountAverageReduceStage)
		  .print()

		env.execute()
	}
}

class CountAverageReduceStage extends RichFlatMapFunction[(Long, Double), (Long, Double)] {

	//定义ReducingState
	private var reducingState: ReducingState[Double] = _

	//定义一个计数器
	var counter = 0L

	override def open(parameters: Configuration): Unit = {

		val reduceSum = new ReducingStateDescriptor[Double]("reduceSum", new ReduceFunction[Double] {
			override def reduce(value1: Double, value2: Double): Double = {
				value1 + value2
			}
		}, classOf[Double])

		//初始化获取reducingState对象
		reducingState = getRuntimeContext.getReducingState[Double](reduceSum)
	}

	override def flatMap(input: (Long, Double), out: Collector[(Long, Double)]): Unit = {
		//计数器+1
		counter += 1

		//添加数据到reducingState
		reducingState.add(input._2)

		out.collect(input._1, reducingState.get() / counter)
	}
}
