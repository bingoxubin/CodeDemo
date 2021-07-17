package com.bingoabin.dataset.transformation

import org.apache.flink.api.common.functions.JoinFunction
import org.apache.flink.api.scala.ExecutionEnvironment
import scala.collection.mutable.ArrayBuffer

object Left_Right_OuterJoin {
	def main(args: Array[String]): Unit = {
		val environment: ExecutionEnvironment = ExecutionEnvironment.getExecutionEnvironment
		import org.apache.flink.api.scala._
		val array1 = ArrayBuffer((1, "张三"), (2, "李四"), (3, "王五"), (4, "张飞"))
		val array2 = ArrayBuffer((1, "18"), (2, "35"), (3, "42"), (5, "50"))
		val firstDataStream: DataSet[(Int, String)] = environment.fromCollection(array1)
		val secondDataStream: DataSet[(Int, String)] = environment.fromCollection(array2)
		//左外连接
		val leftOuterJoin: UnfinishedOuterJoinOperation[(Int, String), (Int, String)] = firstDataStream.leftOuterJoin(secondDataStream)
		//where指定左边流关联的字段 ，equalTo指定与右边流相同的字段
		val leftDataSet: JoinFunctionAssigner[(Int, String), (Int, String)] = leftOuterJoin.where(0).equalTo(0)
		//对关联的数据进行函数操作
		val leftResult: DataSet[(Int, String, String)] = leftDataSet.apply(new JoinFunction[(Int, String), (Int, String), (Int, String, String)] {
			override def join(left: (Int, String), right: (Int, String)): (Int, String, String) = {
				val result = if (right == null) {
					Tuple3[Int, String, String](left._1, left._2, "null")
				} else {
					Tuple3[Int, String, String](left._1, left._2, right._2)
				}
				result
			}
		})
		leftResult.print()
		//右外连接
		val rightOuterJoin: UnfinishedOuterJoinOperation[(Int, String), (Int, String)] = firstDataStream.rightOuterJoin(secondDataStream)
		//where指定左边流关联的字段 ，equalTo指定与右边流相同的字段
		val rightDataSet: JoinFunctionAssigner[(Int, String), (Int, String)] = rightOuterJoin.where(0).equalTo(0)
		//对关联的数据进行函数操作
		val rightResult: DataSet[(Int, String, String)] = rightDataSet.apply(new JoinFunction[(Int, String), (Int, String), (Int, String, String)] {
			override def join(left: (Int, String), right: (Int, String)): (Int, String, String) = {
				val result = if (left == null) {
					Tuple3[Int, String, String](right._1, right._2, "null")
				} else {
					Tuple3[Int, String, String](right._1, right._2, left._2)
				}
				result
			}
		})
		rightResult.print()
	}
}
