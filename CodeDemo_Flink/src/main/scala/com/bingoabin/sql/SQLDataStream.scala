package com.bingoabin.sql
//#SQL 作为 Flink 中提供的接口之一，占据着非常重要的地位，主要是因为 SQL 具有灵活和丰富的语法，能够应用于大部分的计算场景。
//#Flink SQL 底层使用 Apache Calcite 框架， 将标准的 Flink SQL 语句解析并转换成底层的算子处理逻辑，并在转换过程中基于语法规则层面进行性能优化，比如谓词下推等。另外用户在使用 SQL 编写 Flink 应用时，能够屏蔽底层技术细节，能够更加方便且高效地通过SQL语句来构建Flink应用。
//#Flink SQL构建在Table API 之上，并含盖了大部分的 Table API 功能特性。同时 Flink SQL 可以和 Table API 混用，Flink 最终会在整体上将代码合并在同一套代码逻辑中

import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.table.api.Table
import org.apache.flink.table.api.scala.StreamTableEnvironment
import org.apache.flink.types.Row

object SQLDataStream {
	//todo:定义样例类
	case class User(id: Int, name: String, age: Int)

	def main(args: Array[String]): Unit = {
		//todo:1、构建流处理环境
		val streamEnv: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
		streamEnv.setParallelism(1)
		//todo:2、构建TableEnvironment
		val tableEnvironment: StreamTableEnvironment = StreamTableEnvironment.create(streamEnv)
		import org.apache.flink.api.scala._
		/**
		 * 101,zhangsan,18
		 * 102,lisi,20
		 * 103,wangwu,25
		 * 104,zhaoliu,15
		 */
		//todo:3、接受socket数据
		val socketStream: DataStream[String] = streamEnv.socketTextStream("node01", 9999)

		//todo:4、对数据进行处理
		val userStream: DataStream[User] = socketStream.map(x => x.split(",")).map(x => User(x(0).toInt, x(1), x(2).toInt))

		//todo:5、将流注册成一张表
		tableEnvironment.registerDataStream("userTable", userStream)

		//todo:6、使用table 的api查询年龄大于20岁的人
		val result: Table = tableEnvironment.sqlQuery("select * from userTable where age >20")
		//todo：7、将table转化成流
		tableEnvironment.toAppendStream[Row](result).print()
		//todo:8、启动
		tableEnvironment.execute("TableFromDataStream")
	}
}

//#发送数据
//101,zhangsan,18
//102,lisi,20
//103,wangwu,25
//104,zhaoliu,15

//#将Table转换成为DataStream的两种模式
//#第一种方式：AppendMode
//将表附加到流数据，表当中只能有查询或者添加操作，如果有update或者delete操作，那么就会失败。
//只有在动态Table仅通过INSERT时才能使用此模式，即它仅附加，并且以前发出的结果永远不会更新。如果更新或删除操作使用追加模式会失败报错。
//#第二种模式：RetraceMode
//始终可以使用此模式。返回值是boolean类型。它用true或false来标记数据的插入和撤回，返回true代表数据插入，false代表数据的撤回。