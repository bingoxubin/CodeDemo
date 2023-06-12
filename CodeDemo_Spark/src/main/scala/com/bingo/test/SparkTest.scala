package com.bingo.test

import org.apache.spark.sql.{DataFrame, SparkSession}

object SparkTest {
	def main(args: Array[String]): Unit = {
		val ss = SparkSession.builder().master("local").getOrCreate()
		val frame: DataFrame = ss.createDataFrame(Seq((1, 2, 3)))
		frame.show()
	}
}
