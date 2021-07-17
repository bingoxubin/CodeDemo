package com.bingoabin.dataset.transformation

import org.apache.flink.api.common.operators.Order
import org.apache.flink.api.scala.ExecutionEnvironment
import scala.collection.mutable.ArrayBuffer

//First-n
//获取集合中的前N个元素
//Sort Partition
//在本地对数据集的所有分区进行排序，通过sortPartition()的链接调用来完成对多个字段的排序
object TopN_SortPartition {
	def main(args: Array[String]): Unit = {
		val environment: ExecutionEnvironment = ExecutionEnvironment.getExecutionEnvironment
		import org.apache.flink.api.scala._
		//数组
		val array = ArrayBuffer((1, "张三", 10), (2, "李四", 20), (3, "王五", 30), (3, "赵6", 40))
		val collectionDataSet: DataSet[(Int, String, Int)] = environment.fromCollection(array)
		//获取前3个元素
		collectionDataSet.first(3).print()
		collectionDataSet
		  .groupBy(0) //按照第一个字段进行分组
		  .sortGroup(2, Order.DESCENDING) //按照第三个字段进行排序
		  .first(1) //获取每组的前一个元素
		  .print()

		/**
		 * 不分组排序，针对所有元素进行排序，第一个元素降序，第三个元素升序
		 */
		collectionDataSet.sortPartition(0, Order.DESCENDING).sortPartition(2, Order.ASCENDING).print()
	}
}
