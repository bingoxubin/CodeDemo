package com.bingoabin.datastream.source

import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.api.windowing.time.Time

object File {
	def main(args: Array[String]): Unit = {
		//构建流处理的环境
		val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
		//从socket获取数据
		//readTextFile(path)
		//读取文本文件，文件遵循TextInputFormat读取规则，逐行读取并返回。
		val sourceStream: DataStream[String] = env.readTextFile("E:\\60.test\\test.txt")
		//导入隐式转换的包
		import org.apache.flink.api.scala._
		//对数据进行处理
		val result: DataStream[(String, Int)] = sourceStream
		  .flatMap(x => x.split(" ")) //按照空格切分
		  .map(x => (x, 1)) //每个单词计为1
		  .keyBy(0) //按照下标为0的单词进行分组
		  .sum(1) //按照下标为1累加相同单词出现的1
		//保存结果
		//result.writeAsText("e:\\result")
		result.print()
		//开启任务
		env.execute("FlinkStream")
	}
}

//原文件：
//nihao java spark hadoop
//hello
//java

//返回：
//11> (hadoop,1)
//1> (nihao,1)
//4> (hello,1)
//2> (java,1)
//1> (spark,1)
//2> (java,2)
