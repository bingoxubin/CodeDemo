package com.bingo.structstreaming

import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.client.{Connection, HTable, Put}
import org.apache.hadoop.hbase.util.Bytes
import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.{ForeachWriter, Row, SparkSession}

object StructHBase {
	def main(args: Array[String]) {
		Logger.getLogger("org").setLevel(Level.ERROR)
		val spark = SparkSession
		  .builder
		  .appName("testWriteResultToHbase")
		  .master("local[8]")
		  .getOrCreate()

		import spark.implicits._
		val lines = spark
		  .readStream
		  .format("kafka")
		  .option("kafka.bootstrap.servers", "node01:9092,node02:9092,node03:9092")
		  .option("subscribe", "test")
		  .load()
		  .selectExpr("cast(topic as String) ", "cast(key as String)", "CAST(value AS STRING)")
		  .as[(String, String, String)]
		lines.createTempView("Originalkafka")
		import spark.sql
		val count = sql("select value from Originalkafka ")
		val query = count.writeStream
		  .outputMode("append")
		  .foreach(new ForeachWriter[Row] {

			  var connection: Connection = null

			  def open(partitionId: Long, version: Long): Boolean = {
				  true
			  }

			  def process(record: Row): Unit = {
				  val conf = new HBaseConfiguration()
				  conf.set("hbase.zookeeper.quorum", "node01:2181,node02:2181")
				  conf.set("hbase.zookeeper.property.clientPort", "2181")
				  val table = new HTable(conf, "hbase_save")
				  val theput = new Put(Bytes.toBytes(record.mkString))
				  theput.add(Bytes.toBytes("f1"), Bytes.toBytes("age"), Bytes.toBytes("30"))
				  table.put(theput)
			  }

			  def close(errorOrNull: Throwable): Unit = {
			  }
		  })
		  .queryName("test")
		  .format("foreach")
		  .start()
		query.awaitTermination()
	}
}