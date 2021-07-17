package com.bingoabin.distributedcache

import org.apache.commons.io.FileUtils
import org.apache.flink.api.common.functions.RichMapFunction
import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.configuration.Configuration

//flink的分布式缓存使用
object FlinkDistributedCache {
	def main(args: Array[String]): Unit = {
		val env = ExecutionEnvironment.getExecutionEnvironment
		import org.apache.flink.api.scala._
		//准备数据集
		val scoreDataSet = env.fromElements((1, "语文", 50), (2, "数学", 60), (3, "英文", 80))
		//todo:1、注册分布式缓存文件
		env.registerCachedFile("E:\\distribute_cache_student.txt", "student")
		//对成绩数据集进行map转换，将（学生ID, 学科, 分数）转换为（学生姓名，学科，分数）
		val result: DataSet[(String, String, Int)] = scoreDataSet.map(new RichMapFunction[(Int, String, Int), (String, String, Int)] {
			var list: List[(Int, String)] = _

			//初始化方法
			override def open(parameters: Configuration): Unit = {
				//获取分布式缓存的文件
				val file = getRuntimeContext.getDistributedCache.getFile("student")
				//获取文件的内容
				import scala.collection.JavaConverters._
				val listData: List[String] = FileUtils.readLines(file).asScala.toList
				//将文本转换为元组（学生ID，学生姓名)
				list = listData.map {
					line => {
						val array = line.split(",")
						(array(0).toInt, array(1))
					}
				}
			}

			//在map方法中使用分布式缓存数据进行转换
			override def map(value: (Int, String, Int)): (String, String, Int) = {
				//获取学生id
				val studentId: Int = value._1
				val studentName: String = list.filter(x => studentId == x._1)(0)._2
				//封装结果返回
				// 将成绩数据(学生ID，学科，成绩) -> (学生姓名，学科，成绩)
				(studentName, value._2, value._3)
			}
		})
		result.print()
	}
}
