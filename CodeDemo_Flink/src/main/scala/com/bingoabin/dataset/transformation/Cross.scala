package com.bingoabin.dataset.transformation

import org.apache.flink.api.scala.ExecutionEnvironment
import scala.collection.mutable.ArrayBuffer

//获取两个数据集的笛卡尔积
object Cross {
	def main(args: Array[String]): Unit = {
		val environment: ExecutionEnvironment = ExecutionEnvironment.getExecutionEnvironment
		import org.apache.flink.api.scala._
		val array1 = ArrayBuffer((1, "张三"), (2, "李四"), (3, "王五"), (4, "张飞"))
		val array2 = ArrayBuffer((1, "18"), (2, "35"), (3, "42"), (5, "50"))
		val firstDataStream: DataSet[(Int, String)] = environment.fromCollection(array1)
		val secondDataStream: DataSet[(Int, String)] = environment.fromCollection(array2)
		//cross笛卡尔积
		val crossDataSet: CrossDataSet[(Int, String), (Int, String)] = firstDataStream.cross(secondDataStream)
		crossDataSet.print()
	}
}
