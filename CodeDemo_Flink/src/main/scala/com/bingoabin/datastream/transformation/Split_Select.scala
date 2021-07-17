package com.bingoabin.datastream.transformation

//根据规则把一个数据流切分为多个流
//应用场景：
//可能在实际工作中，源数据流中混合了多种类似的数据，多种类型的数据处理规则不一样，所以就可以在根据一定的规则，
//把一个数据流切分成多个数据流，这样每个数据流就可以使用不同的处理逻辑了

import java.{lang, util}
import org.apache.flink.streaming.api.collector.selector.OutputSelector
import org.apache.flink.streaming.api.scala.{DataStream, SplitStream, StreamExecutionEnvironment}

object Split_Select {
	def main(args: Array[String]): Unit = {
		val environment: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
		environment.setParallelism(1)
		import org.apache.flink.api.scala._
		//构建DataStream
		val firstStream: DataStream[String] = environment.fromCollection(Array("hadoop hive", "spark flink"))
		val selectStream: SplitStream[String] = firstStream.split(new OutputSelector[String] {
			override def select(value: String): lang.Iterable[String] = {
				var list = new util.ArrayList[String]()
				//如果包含hello字符串
				if (value.contains("hadoop")) {
					//存放到一个叫做first的stream里面去
					list.add("first")
				} else {
					//否则存放到一个叫做second的stream里面去
					list.add("second")
				}
				list
			}
		})
		//获取first这个stream
		selectStream.select("first").print()
		environment.execute()
	}
}
