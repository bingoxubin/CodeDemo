package com.bingo.mysql

import org.apache.spark.sql.{SaveMode, SparkSession}

import java.util.Properties

object MysqlInsert {
	def main(args: Array[String]): Unit = {
		val spark = SparkSession.builder().appName("MysqlInsertDemo").master("local").getOrCreate()
		val df = spark.read.option("header", "true").csv("CodeDemo_Spark/src/main/resources/USER_T.csv")
		df.show()
		val url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8"
		val prop = new Properties()
		prop.put("user", "root")
		prop.put("password", "111111")
		df.write.mode(SaveMode.Append).jdbc(url, "USER_T", prop)
	}
}
