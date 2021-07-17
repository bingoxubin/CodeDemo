package com.bingoabin.dataset.transformation

import org.apache.flink.api.scala.ExecutionEnvironment
import scala.collection.mutable.ArrayBuffer

//返回一个数据集中去重之后的元素，data.distinct()
object Distinct {
	def main(args: Array[String]): Unit = {
		val environment: ExecutionEnvironment = ExecutionEnvironment.getExecutionEnvironment
		import org.apache.flink.api.scala._
		val arrayBuffer = new ArrayBuffer[String]()
		arrayBuffer.+=("hello world1")
		arrayBuffer.+=("hello world2")
		arrayBuffer.+=("hello world3")
		arrayBuffer.+=("hello world4")
		val collectionDataSet: DataSet[String] = environment.fromCollection(arrayBuffer)
		val dsDataSet: DataSet[String] = collectionDataSet.flatMap(x => x.split(" ")).distinct()
		dsDataSet.print()
	}
}
