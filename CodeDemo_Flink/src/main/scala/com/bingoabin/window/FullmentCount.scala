package com.bingoabin.window
//# window窗口数据的集合统计
//#前面我们可以通过aggregrate实现数据的聚合，对于求最大值，最小值，平均值等操作，我们也可以通过process方法来实现
//#对于某一个window内的数值统计，我们可以增量的聚合统计或者全量的聚合统计
//
//#等到窗口截止，或者窗口内的数据全部到齐，然后再进行统计，可以用于求窗口内的数据的最大值，或者最小值，平均值等
//#等属于窗口的数据到齐，才开始进行聚合计算【可以实现对窗口内的数据进行排序等需求】
//1.apply(windowFunction)
//2.process(processWindowFunction)
//3.processWindowFunction比windowFunction提供了更多的上下文信息。
//需求：通过全量聚合统计，求取每3条数据的平均值

import org.apache.flink.api.java.tuple.Tuple
import org.apache.flink.streaming.api.scala.function.ProcessWindowFunction
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.api.windowing.windows.GlobalWindow
import org.apache.flink.util.Collector

//求取每3条数据的平均值
object FullmentCount {
	/**
	 * 输入数据
	 * 1
	 * 2
	 * 3
	 * 4
	 * 5
	 * 6
	 *
	 * @param args
	 */
	def main(args: Array[String]): Unit = {
		val environment: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

		import org.apache.flink.api.scala._

		val socketStream: DataStream[String] = environment.socketTextStream("node01", 9999)

		//统计一个窗口内的数据的平均值
		socketStream.map(x => (1, x.toInt))
		  .keyBy(0)
		  .countWindow(3)
		  //通过process方法来统计窗口的平均值
		  .process(new MyProcessWindowFunctionclass)
		  .print()

		//必须调用execute方法，否则程序不会执行
		environment.execute("count avg")
	}
}

/** ProcessWindowFunction 需要跟四个参数
 * 输入参数类型，输出参数类型，聚合的key的类型，window的下界
 *
 */
class MyProcessWindowFunctionclass extends ProcessWindowFunction[(Int, Int), Double, Tuple, GlobalWindow] {

	override def process(key: Tuple, context: Context, elements: Iterable[(Int, Int)], out: Collector[Double]): Unit = {
		var totalNum = 0;
		var countNum = 0;
		for (data <- elements) {
			totalNum += 1
			countNum += data._2
		}
		out.collect(countNum / totalNum)
	}
}
