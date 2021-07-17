package com.bingoabin.dataset.connector

import org.apache.flink.addons.hbase.TableInputFormat
import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.configuration.Configuration
import org.apache.hadoop.hbase.{Cell, HBaseConfiguration, HConstants, TableName}
import org.apache.hadoop.hbase.client._
import org.apache.hadoop.hbase.util.Bytes
import org.apache.flink.api.java.tuple

//flink从hbase表中读取数据
object ReadHBase {
	def main(args: Array[String]): Unit = {
		//获取批处理的环境
		val env: ExecutionEnvironment = ExecutionEnvironment.getExecutionEnvironment
		import org.apache.flink.api.scala._
		//通过InputFormat添加数据源
		val hbaseDataSet = env.createInput(new TableInputFormat[tuple.Tuple2[String, String]] {
			//初始化配置方法
			override def configure(parameters: Configuration): Unit = {
				val conf = HBaseConfiguration.create();
				conf.set(HConstants.ZOOKEEPER_QUORUM, "node01,node02,node03")
				conf.set(HConstants.ZOOKEEPER_CLIENT_PORT, "2181")
				val conn: Connection = ConnectionFactory.createConnection(conf)
				table = classOf[HTable].cast(conn.getTable(TableName.valueOf("hbasesource")))
				scan = new Scan() {
					addFamily(Bytes.toBytes("f1"))
				}
			}

			override def getTableName: String = {
				"hbasesource"
			}

			override def getScanner: Scan = {
				scan
			}

			//封装hbase表数据
			override def mapResultToTuple(result: Result): tuple.Tuple2[String, String] = {
				//获取rowkey
				val rowkey: String = Bytes.toString(result.getRow)
				val rawCells: Array[Cell] = result.rawCells()
				val sb = new StringBuffer()
				for (cell <- rawCells) {
					val value = Bytes.toString(cell.getValueArray, cell.getValueOffset, cell.getValueLength)
					sb.append(value).append(",")
				}
				val valueString = sb.replace(sb.length() - 1, sb.length(), "").toString
				val tuple2 = new org.apache.flink.api.java.tuple.Tuple2[String, String]
				//给元素的下标赋值
				tuple2.setField(rowkey, 0)
				tuple2.setField(valueString, 1)
				tuple2
			}
		})
		hbaseDataSet.print()
	}
}
