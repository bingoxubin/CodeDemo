package com.bingoabin.state

//回顾单词计数的例子
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}

/**
 * 使用滑动窗口
 * 每隔1秒钟统计最近2秒钟的每个单词出现的次数
 */
object ValueState {

	def main(args: Array[String]): Unit = {
		//构建流处理的环境
		val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

		//从socket获取数据
		val sourceStream: DataStream[String] = env.socketTextStream("node01", 9999)
		//导入隐式转换的包
		import org.apache.flink.api.scala._

		//对数据进行处理
		val result: DataStream[(String, Int)] = sourceStream
		  .flatMap(x => x.split(" ")) //按照空格切分
		  .map(x => (x, 1)) //每个单词计为1
		  .keyBy(0) //按照下标为0的单词进行分组
		  .sum(1) //按照下标为1累加相同单词出现的1
		//对数据进行打印
		result.print()

		//开启任务
		env.execute("FlinkStream")
	}
}

//输入
//hadoop hadoop
//hadoop
//hive hadoop
//输出
//8> (hadoop,1)
//1> (hive,1)
//8> (hadoop,2)
//8> (hadoop,3)
//8> (hadoop,4)


//Flink中有两种基本类型的State, Keyed State和Operator State，他们两种都可以以两种形式存在：
//原生状态(raw state)
//由算子自己管理数据结构，当触发Checkpoint操作过程中，Flink并不知道状态数据内部的数据结构，只是将数据转换成bytes数据存储在Checkpoints中，当从Checkpoints恢复任务时，算子自己再反序列化出状态的数据结构。

//托管状态(managed state)
//1.由Flink Runtime控制和管理状态数据，并将状态数据转换成为内存的Hash tables或 RocksDB的对象存储，然后将这些数据通过内部的接口持久化到checkpoints中，任务异常时可以通过这些状态数据恢复任务。
//2.推荐使用ManagedState管理状态数据，ManagedState更好的支持状态数据的重平衡以及更加完善的内存管理

//|              | Managed State                                    | Raw State        |
//| ------------ | ------------------------------------------------ | ---------------- |
//| 状态管理方式 | Flink Runtime托管，自动存储、自动恢复、自动伸缩  | 用户自己管理     |
//| 状态数据结构 | Flink提供的常用数据结构，如ListState、MapState等 | 字节数组：byte[] |
//| 使用场景     | 绝大多数Flink算子                                | 用户自定义算子   |