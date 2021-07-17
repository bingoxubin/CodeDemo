package com.bingoabin.datastream.sink

import java.sql.{Connection, DriverManager, PreparedStatement, Statement}
import java.util.Properties
import com.alibaba.fastjson.JSON
import com.bingoabin.util.PropertiesUtil
import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.api.scala._
import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

object Mysql {
	def main(args: Array[String]): Unit = {
		val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

		val bootstrapServers: String = PropertiesUtil.getStringByKey("bootstrap.servers", "kafka.properties")
		val topicName: String = PropertiesUtil.getStringByKey("topic", "kafka.properties")
		val groupID: String = PropertiesUtil.getStringByKey("group.id", "kafka.properties")

		val properties = new Properties()
		properties.setProperty("bootstrap.servers", bootstrapServers)
		properties.setProperty("topic.name", topicName)
		properties.setProperty("group.id", groupID)

		val kafkaConsumer: FlinkKafkaConsumer[String] = new FlinkKafkaConsumer[String](topicName, new SimpleStringSchema(), properties)
		kafkaConsumer.setStartFromLatest()

		val sourceData: DataStream[String] = env.addSource(kafkaConsumer)
		sourceData.print()
		sourceData.addSink(new MySQLSink()) //写入mysql

		env.execute("FlinkToMysql")
	}
}

class MySQLSink extends RichSinkFunction[String] with Serializable {
	var connection: Connection = _
	var ps: PreparedStatement = _
	var statement: Statement = _
	val username = "root"
	val password = "Gepoint"
	val drivername = "com.mysql.jdbc.Driver"
	val url = "jdbc:mysql://192.168.186.13:3307/person"

	//打开mysql的连接
	override def open(parameters: Configuration): Unit = {
		Class.forName(drivername)
		connection = DriverManager.getConnection(url, username, password)
		statement = connection.createStatement
		connection.setAutoCommit(false)
	}

	//处理数据后写入mysql
	override def invoke(value: String): Unit = {
		val sql = "insert into persontarget(id,name,birthday,idcard) values(?,?,?,?)"
		val jsonvalue = JSON.parseObject(value)
		ps = connection.prepareStatement(sql)
		ps.setString(1, jsonvalue.getString("id"))
		ps.setString(2, jsonvalue.getString("name"))
		ps.setString(3, jsonvalue.getString("birthday"))
		ps.setString(4, jsonvalue.getString("idcard"))
		ps.execute()
		connection.commit()
	}

	//关闭mysql的连接
	override def close(): Unit = {
		if (ps != null) {
			ps.close()
		}
		if (connection != null) {
			connection.close()
		}
	}
}