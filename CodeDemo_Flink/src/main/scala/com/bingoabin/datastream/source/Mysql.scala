package com.bingoabin.datastream.source

import java.sql.{Connection, DriverManager, PreparedStatement}

import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.functions.source.{RichSourceFunction, SourceFunction}
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import java.sql.ResultSet

object Mysql {
	def main(args: Array[String]): Unit = {
		val environment: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
		import org.apache.flink.api.scala._
		val getSource: DataStream[Person] = environment.addSource(new MySQLSource1).setParallelism(1)
		getSource.setParallelism(1).print()
		environment.execute()
	}
}

//继承SourceFunction来自定义单并行度source
class MySQLSource1 extends RichSourceFunction[Person] {
	var connection: Connection = null
	var pstat: PreparedStatement = null

	override def open(parameters: Configuration): Unit = {
		Class.forName("com.mysql.jdbc.Driver") //加载数据库驱动
		connection = DriverManager.getConnection("jdbc:mysql://192.168.186.13:3307/person", "root", "Gepoint") //获取连接
		pstat = connection.prepareStatement("select * from person")
		println("连接成功")
	}

	override def run(sourceContext: SourceFunction.SourceContext[Person]): Unit = {
		try {
			val resultSet: ResultSet = pstat.executeQuery
			while (resultSet.next) {
				val id = resultSet.getString("id")
				val name = resultSet.getString("name")
				val birthday = resultSet.getString("birthday")
				val idcard = resultSet.getString("idcard")
				sourceContext.collect(Person(id, name, birthday, idcard))
			}
		} catch {
			case e: Exception => println(e)
		}
	}

	override def cancel(): Unit = {
		try {
			if (connection != null) connection.close
			if (pstat != null) pstat.close
		} catch {
			case e: Exception => println(e)
		}
	}
}

case class Person(id: String, name: String, birthday: String, idcard: String)
