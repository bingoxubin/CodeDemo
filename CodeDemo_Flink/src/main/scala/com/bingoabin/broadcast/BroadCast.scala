package com.bingoabin.broadcast

import org.apache.flink.api.common.functions.RichMapFunction
import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.configuration.Configuration
import scala.collection.mutable.ArrayBuffer

//flink广播变量使用案例
object BroadCast {
	def main(args: Array[String]): Unit = {
		val environment: ExecutionEnvironment = ExecutionEnvironment.getExecutionEnvironment
		import org.apache.flink.api.scala._
		//准备数据集
		val userInfo = ArrayBuffer(("zs", 10), ("ls", 20), ("ww", 30))
		//加载数据集构建DataSet--需要广播的数据
		val userDataSet: DataSet[(String, Int)] = environment.fromCollection(userInfo)
		//原始数据
		val data = environment.fromElements("zs", "ls", "ww")
		//在这里需要使用到RichMapFunction获取广播变量
		val result = data.map(new RichMapFunction[String, String] {
			//定义一个list集合，用户接受open方法中获取到的广播变量
			var listData: java.util.List[(String, Int)] = null
			//定义一个map集合，存储广播变量中的内容
			var allMap = Map[String, Int]()

			//初始化方法  可以在open方法中获取广播变量数据
			override def open(parameters: Configuration): Unit = {
				//获取广播变量(broadcastMapName)的值
				listData = getRuntimeContext.getBroadcastVariable[(String, Int)]("broadcastMapName")
				val it = listData.iterator()
				while (it.hasNext) {
					val tuple = it.next()
					allMap += (tuple._1 -> tuple._2)
				}
			}

			//使用广播变量操作数据
			override def map(name: String): String = {
				val age = allMap.getOrElse(name, 20)
				name + "," + age
			}
		}).withBroadcastSet(userDataSet, "broadcastMapName")
		result.print()
	}
}
