package com.bingoabin.dataset.transformation

import org.apache.flink.api.scala.ExecutionEnvironment
import scala.collection.mutable.ArrayBuffer

//partition分区算子
object Partition {
	def main(args: Array[String]): Unit = {
		val environment: ExecutionEnvironment = ExecutionEnvironment.getExecutionEnvironment
		import org.apache.flink.api.scala._
		val array = ArrayBuffer((1, "hello"),
			(2, "hello"),
			(2, "hello"),
			(3, "hello"),
			(3, "hello"),
			(3, "hello"),
			(4, "hello"),
			(4, "hello"),
			(4, "hello"),
			(4, "hello"),
			(5, "hello"),
			(5, "hello"),
			(5, "hello"),
			(5, "hello"),
			(5, "hello"),
			(6, "hello"),
			(6, "hello"),
			(6, "hello"),
			(6, "hello"),
			(6, "hello"),
			(6, "hello"))
		environment.setParallelism(2)

		val sourceDataSet: DataSet[(Int, String)] = environment.fromCollection(array)
		//partitionByHash:按照指定的字段hashPartitioner分区
		sourceDataSet.partitionByHash(0).mapPartition(eachPartition => {
			eachPartition.foreach(t => {
				println("当前线程ID为" + Thread.currentThread().getId + "=============" + t._1)
			})
			eachPartition
		}).print()
		//partitionByRange：按照指定的字段进行范围分区
		sourceDataSet.partitionByRange(x => x._1).mapPartition(eachPartition => {
			eachPartition.foreach(t => {
				println("当前线程ID为" + Thread.currentThread().getId + "=============" + t._1)
			})
			eachPartition
		}).print()
	}
}