package com.bingo.sparksql.mysql

import org.apache.spark.sql.SparkSession

object MysqlQuery {
	def main(args: Array[String]): Unit = {
		val spark = SparkSession.builder().appName("MysqlQueryDemo").master("local").getOrCreate()
		val jdbcDF = spark.read
		  .format("jdbc")
		  .option("url", "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8")
		  .option("dbtable", "USER_T")
		  .option("user", "root")
		  .option("password", "111111")
		  .load()
		jdbcDF.show()
	}
}
