package com.bingoabin.dataset.transformation

import org.apache.flink.api.scala.ExecutionEnvironment
import scala.collection.mutable.ArrayBuffer

//内连接
object Join {
	def main(args: Array[String]): Unit = {
		val environment: ExecutionEnvironment = ExecutionEnvironment.getExecutionEnvironment
		import org.apache.flink.api.scala._
		val array1 = ArrayBuffer((1, "张三"), (2, "李四"), (3, "王五"))
		val array2 = ArrayBuffer((1, "18"), (2, "35"), (3, "42"))
		val firstDataStream: DataSet[(Int, String)] = environment.fromCollection(array1)
		val secondDataStream: DataSet[(Int, String)] = environment.fromCollection(array2)
		val joinResult: UnfinishedJoinOperation[(Int, String), (Int, String)] = firstDataStream.join(secondDataStream)
		//where指定左边流关联的字段 ，equalTo指定与右边流相同的字段
		val resultDataSet: DataSet[(Int, String, String)] = joinResult.where(0).equalTo(0).map(x => {
			(x._1._1, x._1._2, x._2._2)
		})
		resultDataSet.print()
	}
}
