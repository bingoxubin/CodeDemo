package com.bingoabin.window
//#Flink窗口中的Evictor移除器
//#流处理中可以使用window分配器和trigger触发器之外，还可以指定一个Evictor移除器，它是一个可选项，它可以在触发器触发后以及应用函数之前或之后从窗口中删除数据元素。
//#Flink DataStream Window内置的三种剔除器: CountEvictor、DeltaEvictor、TimeEvictor
//1.CountEvictor: 数量移除器
//在Window中保留指定数量的元素，并从窗口头部开始丢弃其余元素。
//2.DeltaEvictor: 阈值移除器
//计算Window中最后一个元素与其余每个元素之间的增量，丢弃增量大于或等于阈值的元素。
//3.TimeEvictor: 时间移除器
//保留Window中最近一段时间内的元素，并丢弃其余元素。

//#该数据移除原理
//#Evictor的2个方法：
//evictBefore()：移除窗口元素，在Window Function之前调用。
//evictAfter()：移除窗口元素，在Window Function之后调用。
//#不论是在Window Function之前移除，或是在Window Function之后移除，最终都会调用CountEvictor的evict方法。
//#可以看出CountEvictor的剔除原理
//1. 窗口中数据总条数 <= 要保留的数据条数(maxCount)，不剔除。
//2. 否则，从Window头部遍历并剔除。
//案例
//需求:测试数据为某个用户在某个时刻浏览了某个商品，以及商品的金额信息，需要对数据按照用户id分组，然后利用窗口函数结合触发器和移除器完成对Window Function执行前从Window中剔除元素。

import com.alibaba.fastjson.JSON
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.api.windowing.evictors.CountEvictor
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.api.windowing.triggers.ProcessingTimeTrigger
import org.apache.flink.streaming.api.windowing.windows.TimeWindow
import org.apache.flink.util.Collector
import org.apache.flink.api.scala._
import org.apache.flink.streaming.api.scala.function.ProcessWindowFunction
import org.apache.log4j.{Level, Logger}


/**
 * CountEvictor移除器的使用
 *
 * 测试数据：
 * 某个用户在某个时刻浏览了某个商品，以及商品的金额
 * {"userID": "user_4", "eventTime": "2019-11-09 10:41:32", "productID": "product_1", "productPrice": 10}
 *
 */

case class UserActionLog(userID: String, eventTime: String, productID: String, productPrice: Double)

object EvictorDemo {
	Logger.getLogger("org").setLevel(Level.ERROR)

	def main(args: Array[String]): Unit = {
		//todo: 构建流处理环境
		val environment: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

		//todo: 接受socket数据
		val socketSource: DataStream[String] = environment.socketTextStream("node01", 9999)

		//todo: 数据处理
		//解析json字符串
		socketSource.map(jsonStr => json2Objecct(jsonStr))
		  //按照userID分组
		  .keyBy(_.userID)
		  //构建timeWindow
		  .timeWindow(Time.seconds(3))
		  // 基于ProcessingTime触发器
		  .trigger(ProcessingTimeTrigger.create())
		  //计数剔除器 表示：Window Function执行前从Window中剔除元素,剔除后Window中只保留3个元素
		  //.evictor(CountEvictor.of(3,false))
		  //窗口函数，对窗口内的数据进行处理
		  .process(new CustomProcessWindowFunction)
		  .print()


		environment.execute("CountEvictorDemo")
	}

	/**
	 * json字符串转换为对象
	 *
	 * @param jsonStr
	 * @return
	 */
	def json2Objecct(jsonStr: String): UserActionLog = {
		val userActionLog: UserActionLog = JSON.parseObject[UserActionLog](jsonStr, classOf[UserActionLog])
		userActionLog
	}

}

//定义ProcessWindowFunction类
class CustomProcessWindowFunction extends ProcessWindowFunction[UserActionLog, String, String, TimeWindow] {

	/**
	 * 对窗口的数据进行聚合处理
	 *
	 * @param key      分组的字段类型
	 * @param context  窗口中的上下文对象
	 * @param elements 窗口内的数据
	 * @param out      输出的结果类型
	 */
	override def process(key: String, context: Context, elements: Iterable[UserActionLog], out: Collector[String]): Unit = {
		//定义一个可变的字符序列
		val allRecords = new StringBuilder
		//遍历
		for (element <- elements) {
			//添加元素
			allRecords.append(element).append("\n")
		}

		val windowStart: Long = context.window.getStart
		val windowEnd: Long = context.window.getEnd
		val result: String = "Key: " + key + " 窗口开始时间: " + windowStart + " 窗口结束时间: " + windowEnd + " 窗口所有数据: \n" + allRecords

		//输出结果
		out.collect(result)
	}
}

//#测试数据
//{"userID": "user_1", "eventTime": "2019-11-09 10:41:32", "productID": "product_1", "productPrice": 10}
//{"userID": "user_1", "eventTime": "2019-11-09 10:41:33", "productID": "product_2", "productPrice": 20}
//{"userID": "user_1", "eventTime": "2019-11-09 10:41:34", "productID": "product_3", "productPrice": 30}
//{"userID": "user_1", "eventTime": "2019-11-09 10:41:35", "productID": "product_4", "productPrice": 40}
//{"userID": "user_1", "eventTime": "2019-11-09 10:41:36", "productID": "product_5", "productPrice": 50}
//{"userID": "user_1", "eventTime": "2019-11-09 10:41:37", "productID": "product_5", "productPrice": 60}



