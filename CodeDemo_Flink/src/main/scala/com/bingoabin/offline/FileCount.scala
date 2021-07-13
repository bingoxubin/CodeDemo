package com.bingoabin.offline

import org.apache.flink.api.scala.{DataSet, ExecutionEnvironment}

object FileCount {
	def main(args: Array[String]): Unit = {
		//todo:1、构建Flink的批处理环境
		val env: ExecutionEnvironment = ExecutionEnvironment.getExecutionEnvironment
		//todo:2、读取数据文件
		val fileDataSet: DataSet[String] = env.readTextFile("E:\\60.test\\test.txt")
		import org.apache.flink.api.scala._
		//todo: 3、对数据进行处理
		val resultDataSet: AggregateDataSet[(String, Int)] = fileDataSet
		  .flatMap(x => x.split(" "))
		  .map(x => (x, 1))
		  .groupBy(0)
		  .sum(1)
		//todo: 4、打印结果
		resultDataSet.print()
		//todo: 5、保存结果到文件
		resultDataSet.writeAsText("E:\\60.test\\result")
		env.execute("FlinkFileCount")
	}
}
