package com.bingoabin.table
//#2、从DataStream中创建 Table（动态表）
//#需求:使用TableApi完成基于流数据的处理

import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.table.api.{Table, Types}
import org.apache.flink.table.api.scala.StreamTableEnvironment
import org.apache.flink.types.Row

//使用TableApi完成基于流数据的处理
object DataStreamTable {

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
		 * 102,lisi,28
		 * 103,wangwu,25
		 * 104,zhaoliu,30
		 */
		//todo:3、接受socket数据
		val socketStream: DataStream[String] = streamEnv.socketTextStream("node01", 9999)

		//todo:4、对数据进行处理
		val userStream: DataStream[User] = socketStream.map(x => x.split(",")).map(x => User(x(0).toInt, x(1), x(2).toInt))

		//todo:5、将流注册成一张表
		tableEnvironment.registerDataStream("userTable", userStream)

		//todo:6、使用table 的api查询年龄大于20岁的人
		val result: Table = tableEnvironment.scan("userTable").filter("age >20")
		//todo：7、将table转化成流
		tableEnvironment.toAppendStream[Row](result).print()
		//todo:8、启动
		tableEnvironment.execute("TableFromDataStream")
	}
}

//#发送数据
//nc -lk 9999
//101,zhangsan,18
//102,lisi,28
//103,wangwu,25
//104,zhaoliu,30

//#DataStream转换成Table逻辑
//#构建StreamExecutionEnvironment和StreamTableEnvironment对象
//- StreamTableEnvironment.fromDataStream(dataStream: DataStream)
//- StreamTableEnvironment.registerDataStream(dataStream: DataStream)

//#更多的table API操作详细见官网
//https://ci.apache.org/projects/flink/flink-docs-release-1.10/dev/table/tableApi.html
