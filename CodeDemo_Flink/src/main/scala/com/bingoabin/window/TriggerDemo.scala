package com.bingoabin.window
//#Flink窗口中的Trigger触发器
//#Flink窗口函数其他可选的API中还包含：
//1.Trigger触发器:决定何时触发窗口内数据的计算
//2.evictor移除器:用来定义移除某些数据的逻辑,能够在触发器触发以后，窗口函数使用之前或者之后从窗口中清除元素，在使用窗口函数之前被移除的元素将不被处理。
//3.allowedLateness:允许处理迟到的数据
//4.sideOutPutLateData:将迟到的数据放入到侧输出流中
//5.getSideOutput:获取侧输出流

//#Flink中的window操作需要伴随对窗口中的数据进行处理的逻辑，也就是窗口函数，而 Trigger 的作用就是决定何时触发窗口函数中的逻辑执行。
//#每一个window分配器都会有一个默认的Trigger触发器，如果默认的Trigger触发器不满足需求，可以自定义触发器
//
//#常见的Trigger
//#1.EventimeTrigger
//基于EventTime Window的默认触发器
//通过对比Watermark和窗口的Endtime确定是否触发窗口计算，如果Watermark大于Window EndTime则触发，否则不触发，窗口将继续等待。
//#2.ProcessingTime Trigger
//基于ProcessingTime Window的默认触发器
//通过对比ProcessTime和窗口EndTme确定是否触发窗口，如果ProcessTime大于EndTime则触发计算，否则窗口继续等待
//#3.CountTrigger
//基于CountWindow的默认触发器
//根据接入数据量是否超过设定的阙值判断是否触发窗口计算。
//#4.PurgingTrigger
//内部使用，用于清除窗口数据
//#5.NerverTrigger
//永不触发的触发器
//
//#触发器可以访问流的时间属性以及定时器，还可以对state状态编程。所以触发器和process function一样强大。
//#例如我们可以实现一个触发逻辑：
//1.当窗口接收到一定数量的元素时，触发器触发。
//2.当窗口接收到一个特定元素时，触发器触发。
//3.当窗口接收到的元素里面包含特定模式(5秒钟内接收到了两个同样类型的事件)，触发器也可以触发。

//#通过自定义触发器，实现特定的业务需求。
//#需求:自定义触发器实现同时按照计数和时间(processTime)触发窗口计算
//
//#自定义Trigger需要实现的方法说明
//onElement ：每一个数据进入窗口都会触发。
//onEventTime ：根据接入窗口的EventTime进行触发操作
//onProcessTime ： 根据接入窗口的ProcessTime进行触发操作
//clear ： window 销毁的时候被调用，执行窗口及状态数据的清除方法。
//
//#窗口触发方法返回结果的类型
//CONTINUE ：  不进行操作，等待。
//FIRE ： 		触发计算且数据保留。
//PRUGE ： 	窗口内部数据清除且不触发计算。
//FIRE_AND_PURGE : 触发计算并清除对应的数据。

import org.apache.flink.api.common.functions.ReduceFunction
import org.apache.flink.api.common.state.{ReducingState, ReducingStateDescriptor}
import org.apache.flink.api.scala._
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.api.windowing.triggers.{Trigger, TriggerResult}
import org.apache.flink.streaming.api.windowing.windows.TimeWindow

/**
 * 需求： 自定义触发器实现同时按照计数和时间(processTime)触发窗口计算
 * 计数和计时触发窗口计算
 */
object TriggerDemo {
	/**
	 * 业务场景：
	 * 为了方便游客，故宫出口依次停放着摆渡车，用来将游客送到故宫出口处
	 * 摆渡车出发有两种情形：满足任意一种情形，摆渡车就出发
	 * 情景一：游客满了(约定一辆摆渡车最多坐满4人，真实的业务场景是10人)
	 * 情景二：停留的时间到了（每辆摆渡车最多停留3秒，真实的业务场景是15分钟）
	 *
	 */
	def main(args: Array[String]): Unit = {
		//todo: 构建流处理环境
		val environment: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

		//todo: 接受socket数据
		val socketSource: DataStream[String] = environment.socketTextStream("node01", 9999)


		//todo: ①摆渡车停留的时长
		val MAX_WAIT_TIME = 3


		//todo: ②摆渡车最多能搭乘的游客人数
		val MAX_PERSON_COUNT = 4

		//todo: 业务处理
		socketSource.filter(_.trim.nonEmpty)
		  .map(line => {
			  val arr: Array[String] = line.split(",")
			  //摆渡车id
			  val id: String = arr(0).trim
			  (id, 1)
		  })
		  .keyBy(0)
		  .timeWindow(Time.seconds(MAX_WAIT_TIME))
		  .trigger(new MyTrigger(MAX_PERSON_COUNT))
		  .sum(1)
		  .print("自定义触发器执行结果")


		//todo: 启动
		environment.execute("TriggerDemo")


	}
}

/**
 * todo:自定义触发器
 *
 * *@param <T> The type of elements on which this { @code Trigger} works. 输入的元素类型
 * *@param <W> The type of { @link Window Windows} on which this { @code Trigger} can operate.  输出的结果类型
 */
class MyTrigger(var maxCount: Int = 0) extends Trigger[(String, Int), TimeWindow] {

	//todo:定义一个ReducingStateDescriptor, 后续为了构建ReducingState
	private val stateDesc = new ReducingStateDescriptor[Int]("count", new ReduceFunction[Int] {
		override def reduce(value1: Int, value2: Int): Int = {
			value1 + value2
		}
	}, classOf[Int])


	/**
	 * 窗口中每进入一个元素，就计算一次
	 *
	 * @param element   进入的元素
	 * @param timestamp 元素到达的时间
	 * @param window    元素添加到窗口的实例
	 * @param ctx       触发器的上下文信息
	 * @return 窗口触发方法返回结果的类型TriggerResult
	 *         CONTINUE ：       不进行操作，等待。
	 *         FIRE ： 		       触发计算且数据保留。
	 *         PRUGE ： 	        窗口内部数据清除且不触发计算。
	 *         FIRE_AND_PURGE : 触发计算并清除对应的数据。
	 *
	 */

	override def onElement(element: (String, Int), timestamp: Long, window: TimeWindow, ctx: Trigger.TriggerContext): TriggerResult = {
		//todo:注册基于处理时间
		ctx.registerProcessingTimeTimer(window.maxTimestamp())

		//todo:获取reducingState
		val reducingState: ReducingState[Int] = ctx.getPartitionedState(stateDesc)

		//todo: 新增1个用户，reducingState自增加1
		reducingState.add(1)

		//todo: 获取每一个摆渡车的用户数
		val currentCount = reducingState.get()

		//todo: 判断是否满足触发的个数
		if (currentCount >= maxCount) {
			println("摆渡车坐满游客，要发车了哦...")
			println("摆渡车坐满游客 → 窗口的开始时间: " + window.getStart + " 窗口的结束时间:" + window.getEnd)

			//清除reducingState数据
			reducingState.clear()

			//触发窗口计算清空窗口
			TriggerResult.FIRE_AND_PURGE

			//todo: 没有坐满游客
		} else {
			//不进行操作，等待
			TriggerResult.CONTINUE
		}
	}

	/**
	 * 计时触发： 时间是事件时间
	 *
	 * @param time   触发器触发时间
	 * @param window 素添加到Window对象实例
	 * @param ctx    触发器的上下文信息
	 * @return
	 */
	override def onEventTime(time: Long, window: TimeWindow, ctx: Trigger.TriggerContext): TriggerResult = {
		//todo:该案例目前采用的是处理时间,这里不进行任何操作
		TriggerResult.CONTINUE
	}

	/**
	 * 计时触发，时间是处理时间processingtime
	 *
	 * @param time   触发器触发时间
	 * @param window 元素添加到Window对象实例
	 * @param ctx    触发器的上下文信息
	 * @return
	 */
	override def onProcessingTime(time: Long, window: TimeWindow, ctx: Trigger.TriggerContext): TriggerResult = {
		println("摆渡车停留时间到了，要发车了哦...")
		println("摆渡车停留时间到了 → 窗口的开始时间: " + window.getStart + " 窗口的结束时间:" + window.getEnd)
		TriggerResult.FIRE_AND_PURGE
	}

	/**
	 * 清空状态数据
	 *
	 * @param window
	 * @param ctx
	 */
	override def clear(window: TimeWindow, ctx: Trigger.TriggerContext): Unit = {
		//清空状态数据
		ctx.getPartitionedState(stateDesc).clear()

		//删除处理时间
		ctx.deleteProcessingTimeTimer(window.maxTimestamp())

	}

	/**
	 * 该触发器是否支持合并，由于Flink内部是基于taskManager上的slot进行分布式计算。需要把每一个task的状态进行合并
	 *
	 * @return
	 */
	override def canMerge: Boolean = {
		true
	}

	/**
	 * 多个taskManager进程中的slot所维护的线程状态实现合并
	 * 合并多个task中的状态数据
	 *
	 * @param window
	 * @param ctx
	 */
	override def onMerge(window: TimeWindow, ctx: Trigger.OnMergeContext): Unit = {
		ctx.mergePartitionedState(stateDesc)

	}
}

//#测试数据
//rb_1,laowang
//rb_1,MrCang
//rb_1,laoyou
//rb_1,yaoming
//rb_2,bajie
//rb_2,wukong
//rb_2,tangsen
//rb_2,yaoguai
//rb_3,mayun
//rb_3,mahuateng
//rb_3,mamingzhe
//rb_1,tony
//rb_3,madada
//rb_4,xiaoming
//rb_4,xiaohua
