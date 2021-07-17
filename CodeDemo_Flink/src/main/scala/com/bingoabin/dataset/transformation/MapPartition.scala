package com.bingoabin.dataset.transformation

import org.apache.flink.api.scala.{DataSet, ExecutionEnvironment}
import scala.collection.mutable.ArrayBuffer

//类似map，一次处理一个分区的数据【如果在进行map处理的时候需要获取第三方资源链接，建议使用MapPartition】
object MapPartition {
	def main(args: Array[String]): Unit = {
		val environment: ExecutionEnvironment = ExecutionEnvironment.getExecutionEnvironment
		import org.apache.flink.api.scala._
		val arrayBuffer = new ArrayBuffer[String]()
		arrayBuffer.+=("hello world1")
		arrayBuffer.+=("hello world2")
		arrayBuffer.+=("hello world3")
		arrayBuffer.+=("hello world4")
		val collectionDataSet: DataSet[String] = environment.fromCollection(arrayBuffer)
		val resultPartition: DataSet[String] = collectionDataSet.mapPartition(eachPartition => {
			eachPartition.map(eachLine => {
				val returnValue = eachLine + " result"
				returnValue
			})
		})
		resultPartition.print()
	}
}
