package com.bingoabin.dataset.connector

//Flink也可以集成Hbase实现将数据写入到Hbase里面去
//1. 第一种：实现OutputFormat接口
//2. 第二种：继承RichSinkFunction重写父类方法

import java.util
import org.apache.flink.api.common.io.OutputFormat
import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.configuration.Configuration
import org.apache.hadoop.hbase.client._
import org.apache.hadoop.hbase.util.Bytes
import org.apache.hadoop.hbase.{HBaseConfiguration, HConstants, TableName}

//flink写数据到hbase表中
object WriteHBase {
	def main(args: Array[String]): Unit = {
		val environment: ExecutionEnvironment = ExecutionEnvironment.getExecutionEnvironment
		import org.apache.flink.api.scala._
		//准备数据
		val sourceDataSet: DataSet[String] = environment.fromElements("0002,lisi,28", "0003,wangwu,30")
		//使用OutputFormat接口，写数据到hbase表中
		sourceDataSet.output(new HBaseOutputFormat)
		environment.execute()
	}
}

//定义OutputFormat接口
class HBaseOutputFormat extends OutputFormat[String] {
	val zkServer = "node01,node02,node03"
	val port = "2181"
	var conn: Connection = null

	override def configure(parameters: Configuration): Unit = {
	}

	override def open(taskNumber: Int, numTasks: Int): Unit = {
		val config: org.apache.hadoop.conf.Configuration = HBaseConfiguration.create
		config.set(HConstants.ZOOKEEPER_QUORUM, zkServer)
		config.set(HConstants.ZOOKEEPER_CLIENT_PORT, port)
		config.setInt(HConstants.HBASE_CLIENT_OPERATION_TIMEOUT, 30000)
		config.setInt(HConstants.HBASE_CLIENT_SCANNER_TIMEOUT_PERIOD, 30000)
		conn = ConnectionFactory.createConnection(config)
	}

	//写数据的方法
	override def writeRecord(record: String): Unit = {
		val tableName: TableName = TableName.valueOf("hbasesource")
		val cf1 = "f1"
		val array: Array[String] = record.split(",")
		val put: Put = new Put(Bytes.toBytes(array(0)))
		put.addColumn(Bytes.toBytes(cf1), Bytes.toBytes("name"), Bytes.toBytes(array(1)))
		put.addColumn(Bytes.toBytes(cf1), Bytes.toBytes("age"), Bytes.toBytes(array(2)))
		val puts = new util.ArrayList[Put]()
		puts.add(put)
		//设置缓存1m，当达到1m时数据会自动刷到hbase
		val params: BufferedMutatorParams = new BufferedMutatorParams(tableName)
		//设置缓存的大小
		params.writeBufferSize(1024 * 1024)
		val mutator: BufferedMutator = conn.getBufferedMutator(params)
		mutator.mutate(puts)
		mutator.flush()
		puts.clear()
	}

	override def close(): Unit = {
		if (null != conn) {
			conn.close()
		}
	}
}