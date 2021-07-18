package com.bingoabin.window
//# window窗口数据的集合统计
//#前面我们可以通过aggregrate实现数据的聚合，对于求最大值，最小值，平均值等操作，我们也可以通过process方法来实现
//#对于某一个window内的数值统计，我们可以增量的聚合统计或者全量的聚合统计
//
//#窗口当中每加入一条数据，就进行一次统计
//#常用的聚合算子
//1.reduce(reduceFunction)
//2.aggregate(aggregateFunction)
//3.sum()、min()、max()
//4.process(ProcessWindowFunction)
//需求：通过接收socket当中输入的数据，统计每5秒钟数据的累计的值

import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.api.windowing.time.Time

object IncrementCount {
	def main(args: Array[String]): Unit = {
		val environment: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
		import org.apache.flink.api.scala._

		val socketStream: DataStream[String] = environment.socketTextStream("node01", 9999)

		socketStream.map(x => (1, x.toInt))
		  .keyBy(0)
		  .timeWindow(Time.seconds(5))
		  .reduce((c1, c2) => (c1._1, c1._2 + c2._2))
		  .print()

		environment.execute("FlinkTimeCount")
	}
}
