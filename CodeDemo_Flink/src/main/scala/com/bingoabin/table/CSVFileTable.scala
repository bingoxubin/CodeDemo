package com.bingoabin.table
//#1、从文件中创建 Table（静态表）
//
//#需求:读取csv文件，文件内容参见课件当中的flinksql.csv文件，查询年龄大于18岁的人，并将结果写入到csv文件里面去，这里涉及到flink的connect的各种与其他外部系统的连接，参见
//https://ci.apache.org/projects/flink/flink-docs-release-1.9/dev/table/connect.html

import org.apache.flink.api.common.typeinfo.TypeInformation
import org.apache.flink.core.fs.FileSystem.WriteMode
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.table.api.scala.StreamTableEnvironment
import org.apache.flink.table.api.{Table, Types}
import org.apache.flink.table.sinks.CsvTableSink
import org.apache.flink.table.sources.CsvTableSource

//flink table加载csv文件
object CSVFileTable {

	def main(args: Array[String]): Unit = {
		//todo:1、构建流处理环境
		val environment: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

		//todo:2、构建TableEnvironment
		val tableEnvironment: StreamTableEnvironment = StreamTableEnvironment.create(environment)
		//todo:3、构建csv数据源
		val csvSource = CsvTableSource.builder().path("E:\\flinksql.csv")
		  .field("id", Types.INT())
		  .field("name", Types.STRING())
		  .field("age", Types.INT())
		  .fieldDelimiter(",") //字段的分隔符
		  .ignoreParseErrors() //忽略解析错误
		  .ignoreFirstLine() //忽略第一行
		  .build()

		//todo:4、注册表
		tableEnvironment.registerTableSource("myUser", csvSource)

		//todo: 5、查询结果
		val result: Table = tableEnvironment.scan("myUser").filter("age>25").select("id,name,age")
		result.printSchema()

		//todo: 6、构建Sink
		val tableSink = new CsvTableSink("./out/tableSink.txt", "\t", 1, WriteMode.OVERWRITE)

		//todo:7、注册sink
		tableEnvironment.registerTableSink("csvOutputTable",
			Array[String]("f1", "f2", "f3"),
			Array[TypeInformation[_]](Types.INT, Types.STRING, Types.INT),
			tableSink)

		//todo:8、写数据到sink
		result.insertInto("csvOutputTable")

		environment.execute("TableCsvSource")

	}
}
